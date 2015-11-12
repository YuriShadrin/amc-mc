package com.exadel.amc.mc.source.youtube;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.Connector;
import com.exadel.amc.mc.engine.EngineConstants;
import com.exadel.amc.mc.engine.InputDataItem;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.ConnectorException;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.source.youtube.data.YoutubeSearchResult;
import com.exadel.amc.mc.source.youtube.rest.YoutubeClient;

@Component
public class YoutubeConnector implements Connector<YoutubeMetricsData> {

    private static final String KEYS_PROPERTIES = "youtube_keys.properties";
    private Logger log = LoggerFactory.getLogger(YoutubeConnector.class);

    @Value("${" + EngineConstants.MC_KEYS_DIR + "}")
    private String keysDir; 

    @Autowired
    private YoutubeClient youtube;
    
    @Override
    public void init(Source source) throws InitializationException {
        log.debug("Loading Youtube keys...");
        Properties props = new Properties();
        Reader reader;
        try {
            reader = new FileReader(keysDir + File.separator + KEYS_PROPERTIES);
            props.load(reader);
            reader.close();
            youtube.init(props.getProperty("api.key"));
        } catch (IOException ex) {
            throw new InitializationException("Could not read properties from file " + keysDir + KEYS_PROPERTIES, ex);
        }
        log.debug("Youtube keys were loaded.");
    }

    @Override
    public YoutubeMetricsData getMetricsData(InputDataItem dataItem) throws ConnectorException {
        log.debug("Getting Youtube metrics...");
        
        YoutubeMetricsData data = new YoutubeMetricsData();
        YoutubeSearchResult<?>ysr;
        try {
            if("V".equals(dataItem.getType())) {
                ysr = youtube.getVideoById(dataItem.getId());
            } else {
                ysr = youtube.getChannelById(dataItem.getId());
            }
        } catch (Exception ex) {
            throw new ConnectorException("Could not get metrics data.", ex);
        }
        data.setYoutubeSearchResult(ysr);
        log.debug("Youtube metrics were got.");
        return data;
    }
}
