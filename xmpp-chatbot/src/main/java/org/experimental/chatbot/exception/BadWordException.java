package org.experimental.chatbot.exception;

public class BadWordException extends Exception {

    private static final long serialVersionUID = 1L;

    public BadWordException(String message) {
        super(message);
    }

    public BadWordException(Throwable cause) {
        super(cause);
    }

    public BadWordException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadWordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
