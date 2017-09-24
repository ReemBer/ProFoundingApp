package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.auth.LoginRequestDto;
import com.itransition.profunding.model.dto.auth.LoginResponseDto;
import com.itransition.profunding.model.dto.user.UserDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 14:02
 */
public interface AuthenticationService {

    LoginResponseDto login(final LoginRequestDto loginRequestDto);
    UserDto getMe();
}
