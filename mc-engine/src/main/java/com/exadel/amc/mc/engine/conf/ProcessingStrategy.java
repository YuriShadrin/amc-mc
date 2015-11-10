package com.exadel.amc.mc.engine.conf;

/**
 * Strategy of data processing.
 * 
 * Strategy always takes into consideration existing limitations
 * (time frame and amount of requests per one time frame).
 */
public enum ProcessingStrategy {

    /**
     * Serial strategy means that data will be processed one-by-one.
     */
    SERIAL,
    
    /**
     * Stretch strategy means that data will be processed in equal time intervals inside existing time frame.
     */
    STRETCH
    
    
}
