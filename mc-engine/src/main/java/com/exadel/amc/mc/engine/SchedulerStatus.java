package com.exadel.amc.mc.engine;

public class SchedulerStatus implements Cloneable {

    volatile private SchedulerState schedulerState;
    volatile private long startTime, endTime;
    volatile private int initialTaskCount, processedTaskCount, failedTaskCount, remainedTaskCount;
    volatile private double taskProcessingMinTime, taskProcessingMaxTime, taskProcessingAverageTime;

    public SchedulerStatus() {
        schedulerState = SchedulerState.STOPPED;
        taskProcessingMinTime = Long.MAX_VALUE;
        taskProcessingMaxTime = Long.MIN_VALUE;
    } 
    
    public SchedulerState getSchedulerState() {
        return schedulerState;
    }
    public void setSchedulerState(SchedulerState schedulerState) {
        this.schedulerState = schedulerState;
    }

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getInitialTaskCount() {
        return initialTaskCount;
    }
    public void setInitialTaskCount(int initialTaskCount) {
        this.initialTaskCount = initialTaskCount;
    }

    public int getProcessedTaskCount() {
        return processedTaskCount;
    }
    public void setProcessedTaskCount(int processedTaskCount) {
        this.processedTaskCount = processedTaskCount;
    }

    public int getFailedTaskCount() {
        return failedTaskCount;
    }
    public void setFailedTaskCount(int failedTaskCount) {
        this.failedTaskCount = failedTaskCount;
    }

    public int getRemainedTaskCount() {
        return remainedTaskCount;
    }
    public void setRemainedTaskCount(int remainedTaskCount) {
        this.remainedTaskCount = remainedTaskCount;
    }

    public double getTaskProcessingMinTime() {
        return taskProcessingMinTime;
    }
    public void setTaskProcessingMinTime(double taskProcessingMinTime) {
        this.taskProcessingMinTime = taskProcessingMinTime;
    }

    public double getTaskProcessingMaxTime() {
        return taskProcessingMaxTime;
    }
    public void setTaskProcessingMaxTime(double taskProcessingMaxTime) {
        this.taskProcessingMaxTime = taskProcessingMaxTime;
    }

    public double getTaskProcessingAverageTime() {
        return taskProcessingAverageTime;
    }
    public void setTaskProcessingAverageTime(double taskProcessingAverageTime) {
        this.taskProcessingAverageTime = taskProcessingAverageTime;
    }

    @Override
    public String toString() {
        return "SchedulerStatus [schedulerState=" + schedulerState + ", startTime=" + startTime + ", endTime=" + endTime
                + ", initialTaskCount=" + initialTaskCount + ", processedTaskCount=" + processedTaskCount
                + ", failedTaskCount=" + failedTaskCount + ", remainedTaskCount=" + remainedTaskCount
                + ", taskProcessingMinTime=" + taskProcessingMinTime + ", taskProcessingMaxTime="
                + taskProcessingMaxTime + ", taskProcessingAverageTime=" + taskProcessingAverageTime + "]";
    }

    @Override
    public synchronized SchedulerStatus clone() {
        SchedulerStatus clone = new SchedulerStatus();
        clone.endTime = this.endTime;
        clone.failedTaskCount = this.failedTaskCount;
        clone.initialTaskCount = this.initialTaskCount;
        clone.processedTaskCount = this.processedTaskCount;
        clone.remainedTaskCount = this.remainedTaskCount;
        clone.schedulerState = this.schedulerState;
        clone.startTime = this.startTime;
        clone.taskProcessingAverageTime = this.taskProcessingAverageTime;
        clone.taskProcessingMaxTime = this.taskProcessingMaxTime;
        clone.taskProcessingMinTime = this.taskProcessingMinTime;
        return clone;
    }

}
