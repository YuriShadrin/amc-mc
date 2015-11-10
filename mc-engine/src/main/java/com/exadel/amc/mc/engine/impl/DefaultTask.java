package com.exadel.amc.mc.engine.impl;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.MetricsData;
import com.exadel.amc.mc.engine.Task;
import com.exadel.amc.mc.engine.TaskState;
import com.exadel.amc.mc.engine.TaskStatus;
import com.exadel.amc.mc.engine.exception.ConnectorException;
import com.exadel.amc.mc.engine.exception.DataSaverException;
import com.exadel.amc.mc.engine.exception.TaskException;

public class DefaultTask<D extends MetricsData> implements Task {

    protected Connector<D> connector;
    protected DataSaver<D> dataSaver;

    protected DefaultTask(Connector<D> connector, DataSaver<D> dataSaver) {
        this.connector = connector;
        this.dataSaver = dataSaver;
    }

    @Override
    public TaskStatus execute(InputDataItem dataItem) {
        TaskStatus status = createTaskStatus(dataItem);

        D data;
        try {
            data = connector.getMetricsData(dataItem);
        } catch (ConnectorException ex) {
            return fillFailedTaskStatus(status, ex);
        }

        status.setSpentLimits(data.getSpentLimits());

        try {
            dataSaver.saveMetricsData(dataItem, data);
        } catch (DataSaverException ex) {
            return fillFailedTaskStatus(status, ex);
        }

        status.setState(TaskState.COMPLETED);
        return status;
    }

    protected TaskStatus createTaskStatus(InputDataItem dataItem) {
        TaskStatus status = new TaskStatus();
        status.setInputDataItem(dataItem);
        status.setState(TaskState.RUNNING);
        return status;
    }

    protected TaskStatus fillFailedTaskStatus(TaskStatus status, TaskException ex) {
        status.setState(TaskState.FAILED);
        status.setException(ex);
        return status;
    }

}
