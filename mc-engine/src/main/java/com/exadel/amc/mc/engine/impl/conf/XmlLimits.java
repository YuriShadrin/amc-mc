package com.exadel.amc.mc.engine.impl.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="limits")
@XmlAccessorType (XmlAccessType.FIELD)
public class XmlLimits extends AbstractLimits {

    @XmlElement(name = "time-frame")
    private String timeFrame;

    @XmlElement(name = "call-limit")
    private int callLimits;

    @XmlElement(name = "intensity")
    private int intensity;
    
    @Override
    public String getTimeFrame() {
        return timeFrame;
    }

    @Override
    public int getCallLimits() {
        return callLimits;
    }

    @Override
    public int getIntensity() {
        return intensity;
    }

    @Override
    public String toString() {
        return "XmlLimits [timeFrame=" + timeFrame + ", callLimits=" + callLimits + ", intensity=" + intensity + "]";
    }
}
