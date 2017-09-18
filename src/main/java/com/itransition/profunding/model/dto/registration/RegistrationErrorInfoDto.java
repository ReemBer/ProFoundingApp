package com.itransition.profunding.model.dto.registration;

import com.itransition.profunding.model.dto.ErrorInfoDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 13.09.2017 20:21
 */
public class RegistrationErrorInfoDto extends ErrorInfoDto {

    public final String error;

    public RegistrationErrorInfoDto(String requestUrl, Exception exception, RegistrationResponseStatus errorStatus) {
        super(requestUrl, exception);
        this.error = errorStatus.name();
    }
}
