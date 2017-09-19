package com.itransition.profunding.model.dto.project;

import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.model.dto.TagDto;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectCreateDto {

    private String title;
    private String description;
    private String image;
    private Date completionDate;
    private List<FinancialGoalDto> financialGoals;
    private Long totalCost;
    private Set<TagDto> tags;
}
