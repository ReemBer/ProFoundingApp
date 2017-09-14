package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.ProjectDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.service.ProjectService;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:09
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Transformer<Project, ProjectDto> projectTransformer;

    @Override
    public ProjectDto getFullProject(Long id) {
        return projectTransformer.makeDto(projectRepository.findOne(id));
    }

    @Override
    public Project saveProject(ProjectDto projectDto) {
        return projectRepository.save(projectTransformer.makeEntity(projectDto));
    }
}
