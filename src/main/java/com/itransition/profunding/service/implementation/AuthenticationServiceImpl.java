package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.User;
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
    private final AuthUserTransformer authUserTransformer;
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            UsernamePasswordAuthenticationToken authRequest = makeAuthToken(loginRequestDto);
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            if (authResult.isAuthenticated()) {
                User user = getAuthenticatedUser(authResult);
                return makeLoginResponse(user);
            } else {
                throw new JsonException("Authentication failed.");
            }
        } catch (BadCredentialsException exception) {
            throw new JsonException("Username or password was incorrect.", exception);
        }
    }

    private UsernamePasswordAuthenticationToken makeAuthToken(final LoginRequestDto loginRequestDto) {
        String username = Optional.ofNullable(loginRequestDto.getUsername())
                                  .orElseThrow(() -> new BadCredentialsException("Username should be passed"));
        String password = Optional.ofNullable(loginRequestDto.getPassword())
                                  .orElseThrow(() -> new BadCredentialsException("Password should be passed"));
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private User getAuthenticatedUser(final Authentication authResult) {
        JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
        User user = userRepository.findOne(userDetails.getId());
        if(Objects.isNull(user)) {
            throw new JsonException("Such user is not in system.");
        }
        return user;
    }

    private LoginResponseDto makeLoginResponse(final User user) {
        String token = this.authenticationHelper.generateToken(user.getId());
        AuthUserDto authUserDto = authUserTransformer.makeDto(user);
        return new LoginResponseDto(token, authUserDto);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        User authUser = userRepository.findUserByUsername(authentication.getName());
        return authUserTransformer.makeDto(authUser);
    }
}
