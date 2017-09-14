package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:26
 */
@RequiredArgsConstructor
@Service
public class ProjectTransformer implements Transformer<Project, ProjectDto> {

    private final UserRepository userRepository;
    private final Transformer<FinancialGoal, FinancialGoalDto> financialGoalTransformer;
    private final Transformer<Tag, TagDto> tagTransformer;
    private final Transformer<Comment, CommentDto> commentTransformer;
    private final Transformer<ProjectNews, ProjectNewsDto> projectNewsTransformer;

    @Override
    public ProjectDto makeDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setImageLink(project.getImageLink());
        projectDto.setCreatorName(project.getCreatorUser().getUsername());
        projectDto.setFinancialGoals(this.EntityToDtoSet(financialGoalTransformer, project.getFinancialGoals()));
        projectDto.setCurrentAmount(project.getCurrentAmount());
        projectDto.setTags(this.EntityToDtoSet(tagTransformer, project.getTags()));
        projectDto.setComments(this.EntityToDtoSet(commentTransformer, project.getComments()));
        projectDto.setTotalRating(project.getTotalRating());
        projectDto.setPaymentLowerBound(project.getPaymentLowerBound());
        projectDto.setPaymentUpperBound(project.getPaymentUpperBound());
        projectDto.setState(project.getProjectCurrentState().name());
        projectDto.setNews(this.EntityToDtoSet(projectNewsTransformer, project.getProjectNews()));
        return projectDto;
    }

    @Override
    public Project makeEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setImageLink(projectDto.getImageLink());
        project.setCreatorUser(userRepository.findUserByUsername(projectDto.getCreatorName()));
        project.setFinancialGoals(this.DtoToEntitySet(financialGoalTransformer, projectDto.getFinancialGoals()));
        project.setCurrentAmount(projectDto.getCurrentAmount());
        project.setTags(this.DtoToEntitySet(tagTransformer, projectDto.getTags()));
        project.setComments(this.DtoToEntitySet(commentTransformer, projectDto.getComments()));
        project.setTotalRating(project.getTotalRating());
        project.setPaymentLowerBound(projectDto.getPaymentLowerBound());
        project.setPaymentUpperBound(projectDto.getPaymentUpperBound());
        project.setProjectCurrentState(ProjectCurrentState.valueOf(projectDto.getState()));
        project.setProjectNews(this.DtoToEntitySet(projectNewsTransformer, projectDto.getNews()));
        return project;
    }


}
