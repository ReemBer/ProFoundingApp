package com.itransition.profunding.security;

import com.itransition.profunding.service.dto.JsonException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 19:47
 */
public class SecurityHelper {

    public static Authentication getAuthenticationWithCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExsist = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExsist) {
            return authentication;
        }
        throw new JsonException("Authentication failed.");
    }
}
