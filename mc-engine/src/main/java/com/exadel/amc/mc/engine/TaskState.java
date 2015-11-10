package com.exadel.amc.mc.engine;

/**
 * Task state.
 */
public enum TaskState {

    /**
     * Task is running.
     */
    RUNNING,

    /**
     * Task completed.
     */
    COMPLETED,

    /**
     * Task completed but has error.
     */
    FAILED

}
