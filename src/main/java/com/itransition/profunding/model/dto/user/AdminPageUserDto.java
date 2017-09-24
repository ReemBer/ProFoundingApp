package com.itransition.profunding.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 18:58
 */
@Getter
@Setter
@Component
public class AdminPageUserDto {
    private Long id;
    private String username;
    private String role;
    private String image;
    private String scanOfPassport;
    private Boolean isSendConfirm;
    private Boolean isBlocked;
}
