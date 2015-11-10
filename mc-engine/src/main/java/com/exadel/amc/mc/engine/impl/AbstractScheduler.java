package com.exadel.amc.mc.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.InputDataQueue;
import com.exadel.amc.mc.engine.MetricsData;
import com.exadel.amc.mc.engine.Scheduler;
import com.exadel.amc.mc.engine.SchedulerState;
import com.exadel.amc.mc.engine.SchedulerStatus;
import com.exadel.amc.mc.engine.Task;
import com.exadel.amc.mc.engine.TaskStatus;
import com.exadel.amc.mc.engine.conf.Limits;
import com.exadel.amc.mc.engine.conf.ProcessingStrategy;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.engine.exception.SchedulerException;

public abstract class AbstractScheduler<M extends MetricsData> implements Scheduler {

    private Source source;
    private SchedulerStatus schedulerStatus;
    private Object monitor = new Object();
    private Logger log = LoggerFactory.getLogger(Scheduler.class);

    private Thread workThread;
    private SchedulerThread schedulerThread;

    // TODO: move to conf.
    private static final long WAITING_ITEM_SLEEP = 1000L;
    private static final long BETWEEN_TASKS_SLEEP = 100L;

    private static final long MIN_SLEEP_TIME = 100L;

    protected class SchedulerThread implements Runnable {

        protected boolean working = true;
        protected boolean suspended = false;

        protected long suspend() {
            long suspendTime = 0L;
            if (suspended) {
                log.debug("'{}' scheduler work thread suspended.", source.getSourceId());
                synchronized(monitor) {
                    schedulerStatus.setSchedulerState(SchedulerState.SUSPENDED);
                    long ts = currentTime();
                    while (suspended) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException ie) {
                            log.warn("Thread interrupted.", ie);
                        }
                    }
                    schedulerStatus.setSchedulerState(SchedulerState.RUNNING);
                    suspendTime = currentTime() - ts;
                }
                log.debug("'{}' scheduler work thread resumed after {}ms.", source.getSourceId(), suspendTime);
            }
            return suspendTime;
        }

        protected void sleep(long time) {
            time = time - suspend();
            if (time <= 0) {
                return;
            }
            long sleepNum = 0;
            boolean iterrupted = false;
            if (time > MIN_SLEEP_TIME) {
                sleepNum = time / MIN_SLEEP_TIME;
            }
            for (int i=0; working && i<sleepNum; i++) {
                try {
                    Thread.sleep(MIN_SLEEP_TIME);
                } catch (InterruptedException ie) {
                    log.warn("Sleep interrupted.", ie);
                    iterrupted = true;
                    break;
                }
            }
            if (!iterrupted && (time % MIN_SLEEP_TIME > 0)) {
                try {
                    Thread.sleep(time % MIN_SLEEP_TIME);
                } catch (InterruptedException ie) {
                    log.warn("Sleep interrupted.", ie);
                }
            }
        }

        @Override
        public void run() {

            log.debug("'{}' scheduler work thread started.", source.getSourceId());

            Limits limits = source.getLimits();
            long timeFrameLifetime = limits.getTimeFrameMillis();
            if (timeFrameLifetime == 0) {
                timeFrameLifetime = 600;
            }

            long sleepBetweenTasks = 0;

            if(source.getProcessingStrategy() == ProcessingStrategy.STRETCH) {
                if (limits.getCallLimits() > 0) {
                    sleepBetweenTasks = timeFrameLifetime / (limits.getCallLimits() + 1);
                } else {
                    sleepBetweenTasks = BETWEEN_TASKS_SLEEP;
                }
            } else {
                sleepBetweenTasks = BETWEEN_TASKS_SLEEP;
            }

            Task task = createTask(getConnector(), getDataSaver());
            long totalTime = 0L;

            // scheduler loop
            while (working) {

                // time frame loop
                long tfStartTime = currentTime();
                int spentLimits = 0;

                int tfTasksProcessed = 0;
                while (working) {

                    InputDataItem inputDataItem = getInputDataQueue().nextItem();
                    if (inputDataItem != null) {

                        if (!inputDataItem.isDisabled()) {

                            log.debug("Executing task '{}:{}'...", source.getSourceId(), inputDataItem.getId());

                            TaskStatus taskStatus = task.execute(inputDataItem);
                            tfTasksProcessed++;

                            long taskTime = taskStatus.getEndTime() - taskStatus.getStartTime();

                            schedulerStatus.setProcessedTaskCount(schedulerStatus.getProcessedTaskCount() + 1);
                            schedulerStatus.setRemainedTaskCount(getInputDataQueue().size());
                            if (taskStatus.getException() != null) {
                                schedulerStatus.setFailedTaskCount(schedulerStatus.getFailedTaskCount() + 1);
                            }

                            if (schedulerStatus.getTaskProcessingMinTime() > taskTime) {
                                schedulerStatus.setTaskProcessingMinTime(taskTime);
                            }

                            if (schedulerStatus.getTaskProcessingMaxTime() < taskTime) {
                                schedulerStatus.setTaskProcessingMaxTime(taskTime);
                            }

                            long intensityCorrection = 0L;
                            if (limits.getIntensity() > 0) {
                                intensityCorrection = 1000 / limits.getIntensity() - taskTime;
                                if (intensityCorrection < 0) {
                                    intensityCorrection = 0;
                                }
                            }
                            
                            totalTime += taskTime;
                            schedulerStatus.setTaskProcessingAverageTime(totalTime / schedulerStatus.getProcessedTaskCount());

                            spentLimits += taskStatus.getSpentLimits();

                            if (spentLimits >= limits.getCallLimits()) {
                                // no more limits, break time frame loop
//System.err.println("no more limits, break time frame loop");
                                break;
                            }

                            if (sleepBetweenTasks - taskTime > intensityCorrection) {
                                sleep(sleepBetweenTasks - taskTime);
                            } else {
                                sleep(intensityCorrection);
                            }
                        }

                    } else {
                        sleep(WAITING_ITEM_SLEEP);
                    }
                    if (currentTime() - tfStartTime >= timeFrameLifetime) {
                        // no more time, break time frame loop
//System.err.println("no more time, break time frame loop " + timeFrameLifetime);
                        break;
                    } 

                    schedulerStatus.setEndTime(currentTime());
                } // time frame loop

                if (tfTasksProcessed > 0) {
                    long tfEndTime = currentTime();
                    log.debug("Time frame loop finished, tasks processed {}, spent time {}ms.", tfTasksProcessed, tfEndTime - tfStartTime);
                }

                // wait for a new time frame (mainly if call limit exceeded))
                long remainTime = tfStartTime + timeFrameLifetime - currentTime();
                if (remainTime > 0 && working) {
                    log.debug("Slepping {}ms...", remainTime);
                    sleep(remainTime);
                }

            }

            log.debug("'{}' scheduler work thread stopped.", source.getSourceId());
            schedulerStatus.setEndTime(currentTime());
            schedulerStatus.setSchedulerState(SchedulerState.STOPPED);
        }
    }

    @Override
    public void init(Source source) throws InitializationException {
        this.source = source;
        this.schedulerStatus = new SchedulerStatus(); 
        getConnector().init(source);
        getDataSaver().init(source);
        getInputDataQueue().init(source);
        schedulerThread = new SchedulerThread();
        workThread = new Thread(schedulerThread, source.getSourceId() + "-thread");
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }

    private void startWorkThread() {
        schedulerThread.working = true;
        workThread.start();
    }

    private void stopWorkThread() {
        schedulerThread.working = false;
        if (schedulerThread.suspended) {
            resumeWorkThread();
        }
    }

    private void suspendWorkThread() {
        schedulerThread.suspended = true;
    }

    private void resumeWorkThread() {
        schedulerThread.suspended = false;
        synchronized (monitor) {
            monitor.notify();
        }
    }

    @Override
    public void start() throws SchedulerException {
        schedulerStatus.setStartTime(currentTime());
        schedulerStatus.setInitialTaskCount(getInputDataQueue().size());
        schedulerStatus.setRemainedTaskCount(schedulerStatus.getInitialTaskCount());
        schedulerStatus.setSchedulerState(SchedulerState.RUNNING);
        startWorkThread();
    }

    @Override
    public void stop() throws SchedulerException {
        stopWorkThread();
        schedulerStatus.setEndTime(currentTime());
        schedulerStatus.setSchedulerState(SchedulerState.STOPPED);
    }

    @Override
    public void suspend() throws SchedulerException {
        suspendWorkThread();
        schedulerStatus.setSchedulerState(SchedulerState.SUSPENDED);
    }

    @Override
    public void resume() throws SchedulerException {
        resumeWorkThread();
        schedulerStatus.setSchedulerState(SchedulerState.RUNNING);
    }

    @Override
    public SchedulerStatus getStatus() {
        return schedulerStatus;
    }

    protected Task createTask(Connector<M>connector, DataSaver<M>dataSaver) {
        return new DefaultTask<>(connector, dataSaver);
    }

    protected abstract Connector<M>getConnector();

    protected abstract DataSaver<M>getDataSaver(); 

    protected abstract InputDataQueue getInputDataQueue();

}
