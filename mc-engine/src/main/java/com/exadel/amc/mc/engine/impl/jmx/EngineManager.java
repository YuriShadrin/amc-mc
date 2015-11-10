package com.exadel.amc.mc.engine.impl.jmx;

import com.exadel.amc.mc.engine.exception.EngineException;
import com.exadel.amc.mc.engine.impl.EngineInstance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EngineManager implements EngineManagerMBean {

    private EngineInstance engineInstance;
    private Gson gson;

    public EngineManager(EngineInstance engineInstance) {
        this.engineInstance = engineInstance;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @Override
    public String getConfiguredSources() {
        return gson.toJson(engineInstance.getConfiguredSources());
    }

    @Override
    public String getSchedulerStatus(String sourceId) {
        return gson.toJson(engineInstance.getSchedulerStatus(sourceId));
    }

    public void stop() throws EngineException {
        engineInstance.stop();
    }
    
    public void start() throws EngineException {
        engineInstance.start();
    }

    public void suspend() throws EngineException {
        engineInstance.suspend();
    }

    public void resume() throws EngineException {
        engineInstance.resume();
    }
}
