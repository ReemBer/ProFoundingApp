package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.FinancialGoal;
import com.itransition.profunding.model.dto.FinancialGoalDto;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:38
 */
@Component
public class FinancialGoalTransformer {

    public FinancialGoalDto makeDto(FinancialGoal financialGoal) {
        return new FinancialGoalDto();
    }

    public FinancialGoal makeEntity(FinancialGoalDto financialGoalDto) {
        return new FinancialGoal();
    }
}
