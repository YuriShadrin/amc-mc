package com.exadel.amc.mc.source.wikipedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.ConnectorException;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component
public class WikipediaConnector implements Connector<WikipediaMetricsData> {

    private Logger log = LoggerFactory.getLogger(WikipediaConnector.class);


    @Override
    public void init(Source source) throws InitializationException {
        log.debug("Initializing Wikipedia...");
        log.debug("Wikipedia initialized.");
    }

    @Override
    public WikipediaMetricsData getMetricsData(InputDataItem dataItem) throws ConnectorException {
        log.debug("Getting Wikipedia metrics...");
        
        WikipediaMetricsData data = new WikipediaMetricsData();
        log.debug("Wikipedia metrics were got.");
        return data;
    }
}
