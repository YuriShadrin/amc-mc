package com.exadel.amc.mc.engine.conf;

public interface Source {

    String getSourceId();

    String getSourceName();

    boolean isDisabled();

    Limits getLimits();

    ProcessingStrategy getProcessingStrategy();
}
