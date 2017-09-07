package com.itransition.profunding.security.service;

import com.itransition.profunding.model.DB.User;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.exception.ExpiredTokenAuthenticationException;
import com.itransition.profunding.security.exception.InvalidTokenAuthenticationException;
import com.itransition.profunding.security.model.JwtAuthenticationToken;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 17:58
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLS_IN_SECOND = 1000L;

    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenPayload tokenPayload = getAndDeserializeToken(authentication);
        validateTokenPayload(tokenPayload);
        User user = this.userRepository.findOne(tokenPayload.getUserId());
        checkNotNull(user, "Token does not contain a user id.");
        return new JwtAuthenticationToken(new JwtUserDetails(user));
    }

    private TokenPayload getAndDeserializeToken(Authentication authentication) {
        String token = StringUtils.trimToNull((String) authentication.getCredentials());
        return authenticationHelper.decodeToken(token);
    }

    private void validateTokenPayload(TokenPayload tokenPayload) {
        checkIsExpired(tokenPayload.getExpiration());
        checkNotNull(tokenPayload.getUserId(), "Token does not contain a user id.");
    }

    private void checkNotNull(Object value, String badCauseMessage) {
        if (Objects.isNull(value)) {
            throw new InvalidTokenAuthenticationException(badCauseMessage);
        }
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if (System.currentTimeMillis() / MILLS_IN_SECOND > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
