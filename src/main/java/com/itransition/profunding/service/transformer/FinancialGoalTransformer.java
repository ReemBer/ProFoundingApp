package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.FinancialGoal;
import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:38
 */
@Component
@RequiredArgsConstructor
public class FinancialGoalTransformer {

    private final ProjectRepository projectRepository;

    public FinancialGoalDto makeDto(FinancialGoal financialGoal) {
        return new FinancialGoalDto(financialGoal.getTitle(), financialGoal.getCost(), financialGoal.getRootProject().getId());
    }

    public FinancialGoal makeEntity(FinancialGoalDto financialGoalDto) {
        Project project = projectRepository.findOne(financialGoalDto.getRootProjectId());
        return new FinancialGoal(null, project, financialGoalDto.getTitle(), financialGoalDto.getCost());
    }
}
