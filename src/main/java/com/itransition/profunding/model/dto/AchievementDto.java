package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AchievementDto {

    private String title;
    private String description;
    private String imageLink;
    private Set<UserDto> users;

}
