package com.itransition.profunding.service;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.ProjectDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:08
 */
public interface ProjectService {

    ProjectDto getFullProject(Long id);
    Boolean saveProject(ProjectDto projectDto);
}
