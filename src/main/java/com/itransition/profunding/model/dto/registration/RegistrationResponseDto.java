package com.itransition.profunding.model.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 16:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RegistrationResponseDto {
    private String status;

    public RegistrationResponseDto(RegistrationResponseStatus status) {
        this.status = status.name();
    }
}
