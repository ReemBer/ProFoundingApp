package com.itransition.profunding.service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 13.09.2017 2:39
 */
public interface EmailService {
    boolean sendRegistrationConfirmMessage(String email);
}
