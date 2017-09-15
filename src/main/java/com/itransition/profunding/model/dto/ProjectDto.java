package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    private String description;
    private String content;
    private String image;
    private Date completionDate;
    private Set<FinancialGoalDto> financialGoals;
    private Long totalAmount;
    private Long totalRating;
    private Set<String> tags;
}
