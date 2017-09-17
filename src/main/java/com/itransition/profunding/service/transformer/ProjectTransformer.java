package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:26
 */
@Component
@RequiredArgsConstructor
public class ProjectTransformer extends TransformerService<Project, ProjectDto> {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto buildDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public Project parseDto(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        project.setCreatorUser(userRepository.findOne(projectDto.getUserId()));
        project.setSubscribedUsers(projectRepository.findSubscribedUsers(projectDto.getId()));
        project.setCompletionDate(projectDto.getCompletionDate());
        return project;
    }

}
