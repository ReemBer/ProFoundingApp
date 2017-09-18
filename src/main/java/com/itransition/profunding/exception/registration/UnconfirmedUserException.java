package com.itransition.profunding.exception.registration;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 18.09.2017 13:17
 */
public class UnconfirmedUserException extends RuntimeException {

    public UnconfirmedUserException() {
        super("User not confirmed.");
    }
}
