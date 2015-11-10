package com.exadel.amc.mc.engine.conf;

public interface Limits {

    String getTimeFrame();
    
    int getCallLimits();
    
    int getIntensity();

    long getTimeFrameMillis();
    
}
