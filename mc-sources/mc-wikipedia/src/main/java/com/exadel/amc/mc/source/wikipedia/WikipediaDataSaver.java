package com.exadel.amc.mc.source.wikipedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.DataSaverException;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component
public class WikipediaDataSaver implements DataSaver<WikipediaMetricsData> {

    Logger log = LoggerFactory.getLogger(WikipediaDataSaver.class);
    
    @Override
    public void init(Source source) throws InitializationException {
    }

    @Override
    public void saveMetricsData(InputDataItem dataItem, WikipediaMetricsData data) throws DataSaverException {
        log.debug("Saving Wikipedia metrics...");

    
        log.debug("Wikipedia metrics were saved.");
    }

}
