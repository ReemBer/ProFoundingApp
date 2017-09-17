package com.itransition.profunding.service;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.ProjectDto;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:08
 */
public interface ProjectService {

    ProjectDto getFullProject(Long id);
    List<ProjectDto> getMyProjects();
    List<ProjectDto> getMyFollowedProjects();
    Boolean saveProject(ProjectDto projectDto);
}
