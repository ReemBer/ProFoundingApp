package com.itransition.profunding.controller;

import com.itransition.profunding.exception.auth.AuthenticationFailedException;
import com.itransition.profunding.exception.auth.UserNotFoundException;
import com.itransition.profunding.model.dto.ErrorInfoDto;
import com.itransition.profunding.model.dto.LoginRequestDto;
import com.itransition.profunding.model.dto.LoginResponseDto;
import com.itransition.profunding.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 19:14
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public LoginResponseDto login(@RequestBody final LoginRequestDto loginRequestDto) {
        return authenticationService.login(loginRequestDto);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorInfoDto usernameOrPasswordNotPassed(HttpServletRequest request, Exception exception) {
        return new ErrorInfoDto(request.getRequestURL().toString(), exception);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(AuthenticationFailedException.class)
    public ErrorInfoDto authenticationFailed(HttpServletRequest request, Exception exception) {
        return new ErrorInfoDto(request.getRequestURL().toString(), exception);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorInfoDto userNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorInfoDto(request.getRequestURL().toString(), exception);
    }
}
