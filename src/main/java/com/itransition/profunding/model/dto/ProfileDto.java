package com.itransition.profunding.model.dto;

import com.itransition.profunding.model.dto.project.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProfileDto {
    private Long id;
    private String username;
    private String image;
    private Set<ProjectDto> projects;
    private Set<ProjectDto> followedProjects;
}
