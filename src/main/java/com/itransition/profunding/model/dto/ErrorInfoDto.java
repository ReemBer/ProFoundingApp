package com.itransition.profunding.model.dto;

import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 13.09.2017 18:29
 */
public class ErrorInfoDto {
    public final String requestUrl;
    public final String exceptionMessage;

    public ErrorInfoDto(String requestUrl, Exception exception) {
        this.requestUrl = requestUrl;
        this.exceptionMessage = exception.getLocalizedMessage();
    }
}
