package com.itransition.profunding.controller.advice;

import com.itransition.profunding.exception.auth.JwtAccountLockedException;
import com.itransition.profunding.model.dto.ErrorInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 25.09.2017 7:26
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(JwtAccountLockedException.class)
    public ErrorInfoDto accountBlocked(HttpServletRequest request, Exception exception) {
        return new ErrorInfoDto(request.getRequestURL().toString(), exception);
    }
}
