package com.exadel.amc.mc.engine.impl.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.EngineConstants;
import com.exadel.amc.mc.engine.conf.InstanceConfiguration;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.InitializationException;

@Component("defaultInstanceConfiguration")
public class DefaultInstanceConfiguration implements InstanceConfiguration {

    private Logger log = LoggerFactory.getLogger(InstanceConfiguration.class);
    private List<Source> sources;
    
    @Value("${" + EngineConstants.MC_INSTANCE_DIR + "}")
    private String instanceDirectory;

    static class Wrapper<T> {
        private List<T> items;
        public Wrapper() {
            items = new ArrayList<T>();
        }
        public Wrapper(List<T> items) {
            this.items = items;
        }
        @XmlAnyElement(lax = true)
        public List<T> getItems() {
            return items;
        }
    }

    @Override
    public List<Source> getSources() {
        return sources;
    }

    @Override
    public void init(String instanceId) throws InitializationException {
        String fileName = instanceDirectory + File.separator + "sources.xml";
        log.debug("Loading '{}' configuration from {}", instanceId, fileName);
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class, XmlSource.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            this.sources = unmarshal(unmarshaller, Source.class, fileName);
        } catch (JAXBException ex) {
            throw new InitializationException(ex);
        }
        log.debug("Configuration has been loaded.");
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> unmarshal(Unmarshaller unmarshaller, Class<T> clazz, String xmlLocation) throws JAXBException {
        StreamSource xml = new StreamSource(xmlLocation);
        Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml, Wrapper.class).getValue();
        return wrapper.getItems();
    }
}
