package com.itransition.profunding.model.dto.auth;

import com.itransition.profunding.model.dto.auth.AuthUserDto;
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
    private String image;

    public LoginResponseDto(String token, AuthUserDto authUserDto) {
        this.token = token;
        this.id = authUserDto.getId();
        this.username = authUserDto.getUsername();
        this.role = authUserDto.getRole();
        this.image = authUserDto.getImage();
    }
}
