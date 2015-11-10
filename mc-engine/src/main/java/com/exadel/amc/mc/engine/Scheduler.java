package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.engine.exception.SchedulerException;

/**
 * The Scheduler.
 * Made a schedule and executes tasks in accordance with it.
 * Scheduler takes input data items from InputDataQueue.
 */
public interface Scheduler extends Initializable<Source> {

    /**
     * Initializes scheduler.
     * @param source - source configuration.
     */
    @Override
    void init(Source source) throws InitializationException;

    /**
     * Starts scheduler.
     * Scheduler makes queue of input data items here.
     * @throws SchedulerException
     */
    void start() throws SchedulerException;

    /**
     * Stops scheduler.
     * Next start means scheduler will process data with new queue of input data items.
     * @throws SchedulerException
     */
    void stop() throws SchedulerException;

    /**
     * Suspends scheduler.
     * @throws SchedulerException
     */
    void suspend() throws SchedulerException;
    
    /**
     * Resumes scheduler.
     * @throws SchedulerException
     */
    void resume() throws SchedulerException;
    
    /**
     * Returns current state of scheduler.
     * @return
     */
    SchedulerStatus getStatus();

}
