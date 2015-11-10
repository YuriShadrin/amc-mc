package com.exadel.amc.mc.engine.impl.jmx;

import com.exadel.amc.mc.engine.exception.EngineException;

public interface EngineManagerMBean {

    String getConfiguredSources();

    String getSchedulerStatus(String sourceId);

    void stop() throws EngineException;

    void start() throws EngineException;

    void suspend() throws EngineException;

    void resume() throws EngineException;

}
