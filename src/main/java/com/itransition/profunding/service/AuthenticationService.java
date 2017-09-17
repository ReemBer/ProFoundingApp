package com.itransition.profunding.service;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.AuthUserDto;
import com.itransition.profunding.model.dto.LoginRequestDto;
import com.itransition.profunding.model.dto.LoginResponseDto;
import com.itransition.profunding.model.dto.UserDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 14:02
 */
public interface AuthenticationService {

    LoginResponseDto login(final LoginRequestDto loginRequestDto);
    UserDto getMe();
}
