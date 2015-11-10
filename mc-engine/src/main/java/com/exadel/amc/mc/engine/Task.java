package com.exadel.amc.mc.engine;

/**
 * The task. 
 */
public interface Task {

    /**
     * Executes the task.
     * @param dataItem - input data item.
     * @return task status.
     */
    TaskStatus execute(InputDataItem dataItem);

}
