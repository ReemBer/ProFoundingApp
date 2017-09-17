package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:56
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String image;
    private Set<ProjectDto> projects;
    private Set<ProjectDto> followedProjects;
}
