package com.exadel.amc.mc.source.wikipedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataQueue;
import com.exadel.amc.mc.engine.impl.AbstractScheduler;
import com.exadel.amc.mc.engine.impl.DefaultInputDataQueue;

@Lazy @Component("wikipedia")
public class WikipediaScheduler extends AbstractScheduler<WikipediaMetricsData> {

    @Autowired
    private WikipediaConnector connector;
    
    @Autowired
    private WikipediaDataSaver dataSaver;
    
    @Autowired
    private DefaultInputDataQueue dataQueue; 
    
    @Override
    protected Connector<WikipediaMetricsData> getConnector() {
        return connector;
    }

    @Override
    protected DataSaver<WikipediaMetricsData> getDataSaver() {
        return dataSaver;
    }

    @Override
    protected InputDataQueue getInputDataQueue() {
        return dataQueue;
    }

}
