package com.exadel.amc.mc.engine.exception;

public class ConnectorException extends TaskException {

    private static final long serialVersionUID = 1L;

    public ConnectorException() {
        super();
    }

    public ConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectorException(String message) {
        super(message);
    }

}
