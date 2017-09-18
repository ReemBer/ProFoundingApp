package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.model.dto.project.ProjectCreateDto;
import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.TagRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private final TagRepository tagRepository;
    private final TagTransformer tagTransformer;

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
        Set<Tag> tags = new LinkedHashSet<>(tagTransformer.parseDtoList(new ArrayList<>(projectDto.getTags())));
        switchExistTags(tags);
        project.setTags(tags);
        return project;
    }

    public Project parseDto(ProjectCreateDto projectCreateDto) {
        return modelMapper.map(projectCreateDto, Project.class);
    }

    private void switchExistTags(Set<Tag> tags) {
        List<Tag> tagsDB = tagRepository.findAll();
        for (Tag tagDB : tagsDB) {
            for(Tag tag : tags) {
                if(tag.getTagName().equals(tagDB.getTagName())) {
                    tag.setId(tagDB.getId());
                }
            }
        }
    }
}
