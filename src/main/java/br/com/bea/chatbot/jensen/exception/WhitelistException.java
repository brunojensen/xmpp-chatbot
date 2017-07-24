package br.com.bea.chatbot.jensen.exception;

public class WhitelistException extends Exception {

    private static final long serialVersionUID = 1L;

    public WhitelistException(String message) {
        super(message);
    }

    public WhitelistException(Throwable cause) {
        super(cause);
    }

    public WhitelistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhitelistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
