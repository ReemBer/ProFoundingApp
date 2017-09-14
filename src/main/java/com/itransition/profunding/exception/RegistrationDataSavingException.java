package com.itransition.profunding.exception;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 13.09.2017 20:15
 */
public class RegistrationDataSavingException extends RuntimeException {
    public RegistrationDataSavingException() {
        super();
    }

    public RegistrationDataSavingException(String message) {
        super(message);
    }

    public RegistrationDataSavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationDataSavingException(Throwable cause) {
        super(cause);
    }

    protected RegistrationDataSavingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
