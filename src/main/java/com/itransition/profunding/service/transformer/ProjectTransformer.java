package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:26
 */
@Component
@RequiredArgsConstructor
public class ProjectTransformer extends TransformerService<Project, ProjectDto> {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto buildDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        try {
            projectDto.setCompletionDate(dateFormat.parse(project.getCompletionDate()));
        } catch (ParseException p) {
            projectDto.setCompletionDate(null);
        }
        return projectDto;
    }

    @Override
    public Project parseDto(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        project.setCreatorUser(userRepository.findOne(projectDto.getUserId()));
        project.setSubscribedUsers(projectRepository.findSubscribedUsers(projectDto.getId()));
        project.setCompletionDate(dateFormat.format(projectDto.getCompletionDate()));
        return project;
    }

}
