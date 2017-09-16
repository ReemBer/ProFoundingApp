package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 16:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginResponseDto {
    private String token;
    private Long id;
    private String username;
    private String role;

    public LoginResponseDto(String token, AuthUserDto authUserDto) {
        this.token = token;
        this.id = authUserDto.getId();
        this.username = authUserDto.getUsername();
        this.role = authUserDto.getRole();
    }
}
