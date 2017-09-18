package com.itransition.profunding.model.dto.registration;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 16:23
 */
public enum RegistrationResponseStatus {
    OK,
    SENDING_EMAIL_ERROR,
    SUCH_USER_ALREADY_EXIST,
    REGISTRATION_DATA_NOT_FOUND,
    USER_CREATING_ERROR
}
