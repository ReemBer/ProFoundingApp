package com.itransition.profunding.model.dto.project;

import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.model.dto.TagDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ProjectCreateDto {

    private String title;
    private String description;
    private String image;
    private Date completionDate;
    private List<FinancialGoalDto> finansalGoals;
    private Long totalCost;
    private List<TagDto> tags;
}
