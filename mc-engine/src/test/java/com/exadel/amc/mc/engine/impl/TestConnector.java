package com.exadel.amc.mc.engine.impl;

import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.ConnectorException;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component
public class TestConnector implements Connector<TestMetricsData> {

    @Override
    public void init(Source source) throws InitializationException {
    }

    @Override
    public TestMetricsData getMetricsData(InputDataItem dataItem) throws ConnectorException {
        try {
            Thread.sleep(199);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new TestMetricsData();
    }

}
