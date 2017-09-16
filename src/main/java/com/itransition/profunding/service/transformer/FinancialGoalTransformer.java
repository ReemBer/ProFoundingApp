package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.FinancialGoal;
import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:38
 */
@Component
@RequiredArgsConstructor
public class FinancialGoalTransformer extends TransformerService<FinancialGoal, FinancialGoalDto> {

    private final ProjectRepository projectRepository;

    @Override
    public FinancialGoal parseDto(FinancialGoalDto dto) {
        FinancialGoal financialGoal = modelMapper.map(dto, FinancialGoal.class);
        financialGoal.setRootProject(projectRepository.findOne(dto.getRootProjectId()));
        return financialGoal;
    }

    @Override
    public FinancialGoalDto buildDto(FinancialGoal entity) {
        return modelMapper.map(entity, FinancialGoalDto.class);
    }
}
