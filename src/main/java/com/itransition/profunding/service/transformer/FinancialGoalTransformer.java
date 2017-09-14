package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.FinancialGoal;
import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.service.Transformer;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:38
 */
public class FinancialGoalTransformer implements Transformer<FinancialGoal, FinancialGoalDto> {
    @Override
    public FinancialGoalDto makeDto(FinancialGoal financialGoal) {
        return new FinancialGoalDto();
    }

    @Override
    public FinancialGoal makeEntity(FinancialGoalDto financialGoalDto) {
        return new FinancialGoal();
    }
}
