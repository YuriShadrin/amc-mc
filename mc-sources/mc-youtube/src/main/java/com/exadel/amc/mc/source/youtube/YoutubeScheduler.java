package com.exadel.amc.mc.source.youtube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataQueue;
import com.exadel.amc.mc.engine.impl.AbstractScheduler;
import com.exadel.amc.mc.engine.impl.DefaultInputDataQueue;

@Lazy @Component("youtube")
public class YoutubeScheduler extends AbstractScheduler<YoutubeMetricsData> {

    @Autowired
    private YoutubeConnector connector;
    
    @Autowired
    private YoutubeDataSaver dataSaver;
    
    @Autowired
    private DefaultInputDataQueue dataQueue; 
    
    @Override
    protected Connector<YoutubeMetricsData> getConnector() {
        return connector;
    }

    @Override
    protected DataSaver<YoutubeMetricsData> getDataSaver() {
        return dataSaver;
    }

    @Override
    protected InputDataQueue getInputDataQueue() {
        return dataQueue;
    }

}
