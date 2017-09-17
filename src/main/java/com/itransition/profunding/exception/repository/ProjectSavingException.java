package com.itransition.profunding.exception.repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 16.09.2017 19:21
 */
public class ProjectSavingException extends RuntimeException {
    public ProjectSavingException() {
    }

    public ProjectSavingException(String message) {
        super(message);
    }

    public ProjectSavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectSavingException(Throwable cause) {
        super(cause);
    }

    public ProjectSavingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
