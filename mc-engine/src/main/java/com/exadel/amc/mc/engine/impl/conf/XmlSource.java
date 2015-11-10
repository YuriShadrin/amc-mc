package com.exadel.amc.mc.engine.impl.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.exadel.amc.mc.engine.conf.ProcessingStrategy;
import com.exadel.amc.mc.engine.conf.Source;

@XmlRootElement(name="source")
@XmlAccessorType (XmlAccessType.FIELD)
public class XmlSource implements Source {

    @XmlElement(name = "id")
    private String sourceId;

    @XmlElement(name = "name")
    private String sourceName;

    @XmlElement(name = "disabled")
    private boolean disabled;

    @XmlElement(name = "limits")
    private XmlLimits limits;
    
    @XmlElement(name = "strategy")
    private String processingStrategy = ProcessingStrategy.SERIAL.name();
    
    @Override
    public String getSourceId() {
        return sourceId;
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public XmlLimits getLimits() {
        return limits;
    }

    @Override
    public ProcessingStrategy getProcessingStrategy() {
        return ProcessingStrategy.valueOf(processingStrategy.toUpperCase());
    }

    @Override
    public String toString() {
        return "XmlSource [sourceId=" + sourceId + ", sourceName=" + sourceName + ", disabled=" + disabled + ", limits="
                + limits + ", processingStrategy=" + getProcessingStrategy() + "]";
    }

}
