package com.exadel.amc.mc.source.youtube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.DataSaver;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.DataSaverException;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component
public class YoutubeDataSaver implements DataSaver<YoutubeMetricsData> {

    Logger log = LoggerFactory.getLogger(YoutubeDataSaver.class);
    
    @Override
    public void init(Source source) throws InitializationException {
    }

    @Override
    public void saveMetricsData(InputDataItem dataItem, YoutubeMetricsData data) throws DataSaverException {
        log.debug("Saving Youtube metrics...");

    
        log.debug("Youtube metrics were saved.");
    }

}
