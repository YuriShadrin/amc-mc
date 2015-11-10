package com.exadel.amc.mc.engine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataQueue;

@Lazy @Component("test")
public class TestScheduler extends AbstractScheduler<TestMetricsData> {

    @Autowired
    InputDataQueue testInputDataQueue;
    
    @Autowired
    DataSaver<TestMetricsData>dataSaver;
    
    @Autowired
    Connector<TestMetricsData>connector;
    
    @Override
    protected Connector<TestMetricsData> getConnector() {
        return connector;
    }

    @Override
    protected DataSaver<TestMetricsData> getDataSaver() {
        return dataSaver;
    }

    @Override
    protected InputDataQueue getInputDataQueue() {
        return testInputDataQueue;
    }

}
