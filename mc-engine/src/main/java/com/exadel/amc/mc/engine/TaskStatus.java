package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.exception.TaskException;

public class TaskStatus {

    private TaskState state;
    private long startTime, endTime;
    private int spentLimits;
    private TaskException exception = null;
    private InputDataItem inputDataItem;

    public TaskStatus() {
        setState(TaskState.RUNNING);
    }

    public TaskState getState() {
        return state;
    }
    public void setState(TaskState state) {
        this.state = state;
        switch (state) {
        case RUNNING:
            startTime = System.currentTimeMillis();
            break;
        case COMPLETED:
        case FAILED:
            endTime = System.currentTimeMillis();
            break;
        }

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

    public int getSpentLimits() {
        return spentLimits;
    }

    public void setSpentLimits(int spentLimits) {
        this.spentLimits = spentLimits;
    }

    public TaskException getException() {
        return exception;
    }

    public void setException(TaskException exception) {
        this.exception = exception;
    }

    public InputDataItem getInputDataItem() {
        return inputDataItem;
    }

    public void setInputDataItem(InputDataItem inputDataItem) {
        this.inputDataItem = inputDataItem;
    }
}

