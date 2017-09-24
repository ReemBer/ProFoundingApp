package com.itransition.profunding.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 5:03
 */
@Getter
@Setter
@Component
public class UserViewDto {
    private Long id;
    private String username;
    private String image;
}
