package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.repository.TagRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Period;
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
    private final TagRepository tagRepository;
    private final TagTransformer tagTransformer;
    private final CommentTransformer commentTransformer;

    @Override
    public ProjectDto buildDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        projectDto.setLeftDays(setLeftDays(project.getCompletionDate()));
        projectDto.setComments(commentTransformer.buildDtoList(project.getComments()));
        return projectDto;
    }

    @Override
    public Project parseDto(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        mapFinancialGoals(project);
        setProjectStatus(project, projectDto);
        project.setCreatorUser(userRepository.findOne(projectDto.getUserId()));
        project.setSubscribedUsers(new HashSet<>());
        setProjectTags(project, projectDto);
        if (projectDto.getComments() != null) {
            project.setComments(commentTransformer.parseDtoList(projectDto.getComments()));
        }
        return project;
    }

    private void mapFinancialGoals(Project project) {
        Set<FinancialGoal> financialGoals = project.getFinancialGoals();
        Iterator<FinancialGoal> iterator = financialGoals.iterator();
        while (iterator.hasNext()) {
            FinancialGoal financialGoal = iterator.next();
            financialGoal.setRootProject(project);
        }
    }

    private void setProjectStatus(Project project, ProjectDto projectDto) {
        if (project.getStatus() != null) {
            project.setStatus(ProjectStatus.valueOf(projectDto.getStatus()));
        } else {
            project.setStatus(ProjectStatus.ACTIVE);
        }
    }

    private void setProjectTags(Project project, ProjectDto projectDto) {
        Set<Tag> tags = new LinkedHashSet<>(tagTransformer.parseDtoList(new ArrayList<>(projectDto.getTags())));
        tags = switchExistTags(tags);
        project.setTags(tags);
    }

    private Set<Tag> switchExistTags(Set<Tag> tags) {
        List<Tag> tagsDB = tagRepository.findAll();
        List<Tag> tags1 = new LinkedList<>(tags);
        Set<Tag> result = new HashSet<>();
        for (Tag tag : tags) {
            boolean flag = false;
            for (Tag tagDB : tagsDB) {
                if (tag.getTagName().equals(tagDB.getTagName())) {
                    flag = true;
                    result.add(tagDB);
                    break;
                }
            }
            if (!flag) {
                result.add(tag);
            }
        }
        return result.size() == 0 ? tags : result;
    }

    private Long setLeftDays(Date completionDate) {
        Date timeNow = new Date();
        if (completionDate.before(timeNow)) return 0L;
        return (completionDate.getTime() - timeNow.getTime()) / 1000 / 60 / 60 / 24;
    }
}
