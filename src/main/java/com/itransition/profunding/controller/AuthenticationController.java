package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.LoginRequestDto;
import com.itransition.profunding.model.dto.LoginResponseDto;
import com.itransition.profunding.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
