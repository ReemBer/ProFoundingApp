package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:26
 */
@RequiredArgsConstructor
@Service
public class ProjectTransformer implements Transformer<Project, ProjectDto> {

    private final Transformer<Tag, String> tagTransformer;

     @Autowired
     private final Transformer<FinancialGoal, FinancialGoalDto> financialGoalTransformer;

    @Override
    public ProjectDto makeDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setContent(project.getContent().toString());
        projectDto.setImage(project.getImage());
        projectDto.setCompletionDate(project.getCompletionDate());
        projectDto.setFinancialGoals(this.EntityToDtoSet(financialGoalTransformer, project.getFinancialGoals()));
        projectDto.setTotalAmount(project.getTotalAmount());
        projectDto.setTags(this.EntityToDtoSet(tagTransformer, project.getTags()));
        projectDto.setTotalRating(project.getTotalRating());
        return projectDto;
    }

    @Override
    public Project makeEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setContent(project.getContent());
        project.setImage(projectDto.getImage());
        project.setCompletionDate(projectDto.getCompletionDate());
        project.setFinancialGoals(this.DtoToEntitySet(financialGoalTransformer, projectDto.getFinancialGoals()));
        project.setTotalAmount(projectDto.getTotalAmount());
        project.setTags(this.DtoToEntitySet(tagTransformer, projectDto.getTags()));
        project.setTotalRating(project.getTotalRating());
        return project;
    }


}
