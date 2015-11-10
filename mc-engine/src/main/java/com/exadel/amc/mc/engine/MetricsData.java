package com.exadel.amc.mc.engine;

/**
 * Because metrics are very different from source to source,
 * this interface contains only manage-dependent methods. 
 */
public interface MetricsData {

    /**
     * Returns amount of limits (requests to source)
     * which were spent during interaction with source.
     * 
     * This info is required for Scheduler to calculate start of next Task.
     */
    int getSpentLimits();
}
