package com.itransition.profunding.model.dto.project;

import com.itransition.profunding.model.db.ProjectStatus;
import com.itransition.profunding.model.dto.FinancialGoalDto;
import com.itransition.profunding.model.dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectDto {

    private Long id;
    private String title;
    private Date completionDate;
    private Long leftDays;
    private String description;
    private String content;
    private String image;
    private String status;
    private List<FinancialGoalDto> financialGoals;
    private Long totalCost;
    private Long currentSum;
    private Long userId;
    private Set<TagDto> tags;
}
