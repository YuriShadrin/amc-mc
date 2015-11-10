package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.exception.EngineException;
import com.exadel.amc.mc.engine.exception.InitializationException;

/**
 * The Engine. 
 */
public interface Engine extends Initializable<String> {

    /**
     * Initializes engine instance.
     * @param instanceId - identifier of instance.
     */
    @Override
    void init(String instanceId) throws InitializationException;

    /**
     * Starts engine.
     * Means start all stopped Schedulers.
     * @throws EngineException
     */
    void start() throws EngineException;;
    
    /**
     * Stops engine.
     * Means stop all running Schedulers.
     * @throws EngineException
     */
    void stop() throws EngineException;;
    
    /**
     * Suspend engine.
     * Means suspend all running Schedulers.
     * @throws EngineException
     */
    void suspend() throws EngineException;;
    
    /**
     * Resume engine.
     * Means resume all suspended Schedulers.
     * @throws EngineException
     */
    void resume() throws EngineException;;

}
