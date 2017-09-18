package com.itransition.profunding.model.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 09.09.2017 18:02
 */
@Getter
@Setter
@Component
public class RegistrationRequestDto {
    private String username;
    private String password;
    private String email;
    private String image;
}
