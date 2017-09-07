package com.itransition.profunding.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 12:41
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload {

    private Long userId;
    private Long expiration;
}
