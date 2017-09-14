package com.itransition.profunding.exception.registration;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 2:40
 */
public class RegistrationDataNotFoundException extends RuntimeException {
    public RegistrationDataNotFoundException() {
        super();
    }

    public RegistrationDataNotFoundException(String message) {
        super(message);
    }

    public RegistrationDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationDataNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RegistrationDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
