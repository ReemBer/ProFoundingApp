package com.itransition.profunding.model.dto.auth;

import com.itransition.profunding.model.db.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 16:29
 */
@Getter
@Setter
@NoArgsConstructor
@Component
public class AuthUserDto {
    private Long id;
    private String username;
    private String email;
    private String role;

    public AuthUserDto(Long id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role.name();
    }
}
