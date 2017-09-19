package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;

import java.util.List;
import java.util.Map;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:08
 */
public interface ProjectService {

    ProjectDto getFullProject(Long id);
    List<ProjectDto> getMyProjects();
    List<ProjectDto> getMyFollowedProjects();
    Boolean createProject(ProjectDto projectDto);
    Map<String, List<ProjectPreviewDto>> getMainPageProjects();
}
