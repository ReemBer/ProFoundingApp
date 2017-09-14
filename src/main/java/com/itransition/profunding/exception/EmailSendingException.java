package com.itransition.profunding.exception;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 2:28
 */
public class EmailSendingException extends RuntimeException {
    public EmailSendingException() {
        super();
    }

    public EmailSendingException(String message) {
        super(message);
    }

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendingException(Throwable cause) {
        super(cause);
    }

    protected EmailSendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
