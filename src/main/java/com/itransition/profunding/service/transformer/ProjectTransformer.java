package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:26
 */
@Component
@RequiredArgsConstructor
public class ProjectTransformer {

    private final UserRepository userRepository;
    private final FinancialGoalTransformer financialGoalTransformer;

    public ProjectDto makeDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getRefactor());
        projectDto.setDescription(project.getDescription());
        projectDto.setImage(project.getImage());
        projectDto.setCompletionDate(project.getCompletionDate());
        projectDto.setUserId(project.getCreatorUser().getId());
        projectDto.setFinancialGoals(this.EntityToDtoSet(financialGoalTransformer, project.getFinancialGoals()));
        projectDto.setTotalCost(project.getTotalCost());
        projectDto.setTags(project.getTags()));
        return projectDto;
    }

    public Project makeEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setRefactor(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setImage(projectDto.getImage());
        project.setCompletionDate(projectDto.getCompletionDate());
        project.setCreatorUser(userRepository.findOne(projectDto.getUserId()));
        project.setFinancialGoals(this.DtoToEntitySet(financialGoalTransformer, projectDto.getFinancialGoals()));
        project.setTotalCost(projectDto.getTotalCost());
        return project;
    }

}
