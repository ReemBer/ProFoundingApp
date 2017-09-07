package com.itransition.profunding.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itransition.profunding.security.exception.InvalidTokenAuthenticationException;
import com.itransition.profunding.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 13:03
 */
@Component
@RequiredArgsConstructor
@PropertySource("classpath:custom.properties")
public class AuthenticationHelper {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationHelper.class);

    private final String SECRET = "security.token.secret";

    private Long tokenExpirationTime = 3600L; //In seconds

    private ObjectMapper objectMapper;

    @Resource
    private Environment environment;

    public String generateToken(final Long userId) {
        logger.debug(environment.getProperty(SECRET));
        try {
            TokenPayload payload = getPayload(userId);
            String token = this.objectMapper.writeValueAsString(payload);
            String secret = environment.getProperty(SECRET);
            return JwtHelper.encode(token, new MacSigner(secret)).getEncoded();
        } catch (JsonProcessingException e) {
            logger.error(String.format("Error generating token.\n%s", e));
            throw new InternalAuthenticationServiceException("Error generating token.", e);
        }
    }

    public TokenPayload decodeToken(final String token) {
        checkNotNullToken(token);
        Jwt jwt = JwtHelper.decode(token);
        JwtVerification(jwt);
        return getPayload(jwt);
    }

    private void checkNotNullToken(final String token) throws InvalidTokenAuthenticationException {
        if (Objects.isNull(token)) {
            logger.error("Token is null or blank.");
            throw new InvalidTokenAuthenticationException("Token is null or blank.");
        }
    }

    private void JwtVerification(Jwt jwt) throws InvalidTokenAuthenticationException {
        try {
            jwt.verifySignature(new MacSigner(environment.getProperty(SECRET)));
        } catch (Exception e) {
            logger.error("Token signature verification failure.");
            throw new InvalidTokenAuthenticationException("Token signature verification failure.", e);
        }
    }

    private TokenPayload getPayload(Jwt jwt) throws InvalidTokenAuthenticationException {
        try {
            return this.objectMapper.readValue(jwt.getClaims(), TokenPayload.class);
        } catch (IOException e) {
            throw new InvalidTokenAuthenticationException("Token parsing failed.", e);
        }
    }

    private TokenPayload getPayload(final Long userId) {
        long expirationTime = Instant.now().getEpochSecond() + tokenExpirationTime;
        return new TokenPayload(userId, expirationTime);
    }
}
