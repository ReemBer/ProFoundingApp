package com.itransition.profunding.service.dto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 14:35
 */
public class JsonException extends RuntimeException {

    public JsonException() {
    }

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }
}
