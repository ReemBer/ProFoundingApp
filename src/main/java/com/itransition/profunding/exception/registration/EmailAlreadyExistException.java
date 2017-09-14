package com.itransition.profunding.exception.registration;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 21:06
 */
public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException() {
        super("User with such email already exist.");
    }
}
