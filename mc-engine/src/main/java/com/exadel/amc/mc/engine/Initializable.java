package com.exadel.amc.mc.engine;

import com.exadel.amc.mc.engine.exception.InitializationException;

/**
 * Initialized Instance.
 * 
 * It is used for initializing instances of engine modules, which implement this interface.
 */
public interface Initializable<T> {

    /**
     * Initializes module.
     * @param initData - initialization data, in accordance with module has a different sense.
     * @throws InitializationException if initialization was failed.
     */
    void init(T initData) throws InitializationException;
    
}
