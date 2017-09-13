package com.itransition.profunding.service.implementation;

import com.itransition.profunding.exception.AuthenticationFailedException;
import com.itransition.profunding.exception.UserNotFoundException;
import com.itransition.profunding.model.dto.AuthUserDto;
import com.itransition.profunding.model.dto.LoginRequestDto;
import com.itransition.profunding.model.dto.LoginResponseDto;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.SecurityHelper;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.security.service.AuthenticationHelper;
import com.itransition.profunding.service.AuthenticationService;
import com.itransition.profunding.service.dto.JsonException;
import com.itransition.profunding.service.transformer.AuthUserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 16:37
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authRequest = makeAuthToken(loginRequestDto);
        final Authentication authResult = this.authenticationManager.authenticate(authRequest);
        if (authResult.isAuthenticated()) {
            return makeResponse(authResult);
        } else {
            throw new AuthenticationFailedException("Authentication failed.");
        }
    }

    private UsernamePasswordAuthenticationToken makeAuthToken(final LoginRequestDto loginRequestDto) {
        String username = Optional.ofNullable(loginRequestDto.getUsername())
                                  .orElseThrow(() -> new BadCredentialsException("Username should be passed"));
        String password = Optional.ofNullable(loginRequestDto.getPassword())
                                  .orElseThrow(() -> new BadCredentialsException("Password should be passed"));
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private LoginResponseDto makeResponse(final Authentication authResult) {
        AuthUserDto authUserDto = getAuthUserDto(authResult);
        String token = this.authenticationHelper.generateToken(authUserDto.getId());
        return new LoginResponseDto(token, authUserDto);
    }

    private AuthUserDto getAuthUserDto(final Authentication authResult) {
        JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
        AuthUserDto user = userRepository.getAuthUserById(userDetails.getId());
        if(Objects.isNull(user)) {
            throw new UserNotFoundException("Such user is not in system.");
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        return userRepository.getAuthUserByUsername(authentication.getName());
    }
}
