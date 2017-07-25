package org.experimental.chatbot.api;

public class DriverException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DriverException(String message) {
        super(message);
    }

    public DriverException(Throwable cause) {
        super(cause);
    }

    public DriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
