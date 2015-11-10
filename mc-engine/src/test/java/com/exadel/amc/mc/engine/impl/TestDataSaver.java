package com.exadel.amc.mc.engine.impl;

import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.DataSaverException;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component
public class TestDataSaver implements DataSaver<TestMetricsData> {

    @Override
    public void init(Source source) throws InitializationException {
    }

    @Override
    public void saveMetricsData(InputDataItem dataItem, TestMetricsData data) throws DataSaverException {
        try {
            Thread.sleep(144);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
