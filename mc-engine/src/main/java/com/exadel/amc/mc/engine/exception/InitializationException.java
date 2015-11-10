package com.exadel.amc.mc.engine.exception;

public class InitializationException extends EngineException {

    public InitializationException(Throwable cause) {
        super(cause);
    }

    private static final long serialVersionUID = 1L;

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationException(String message) {
        super(message);
    }

}
