package com.exadel.amc.mc.engine;

public enum SchedulerState {

    ANY,
    
    /**
     * Scheduler is running.
     */
    RUNNING,
    
    /**
     * Scheduler stopped.
     */
    STOPPED,
    
    /**
     * Scheduler suspended.
     */
    SUSPENDED

}
