package com.itransition.profunding.exception.registration;

public class UnconfirmedUserException extends RuntimeException {

    public UnconfirmedUserException() {
        super("User not confirmed.");
    }
}
