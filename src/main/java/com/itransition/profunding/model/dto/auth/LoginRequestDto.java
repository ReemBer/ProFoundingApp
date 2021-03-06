package com.itransition.profunding.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 16:18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginRequestDto {
    private String username;
    private String password;
}
