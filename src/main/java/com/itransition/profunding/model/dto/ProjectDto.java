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
    private String name;
    private String description;
    private String imageLink;
    private Date completionDate;
    private String creatorName;
    private Set<FinancialGoalDto> financialGoals;
    private Long currentAmount;
    private Set<TagDto> tags;
    private Set<CommentDto> comments;
    private Long totalRating;
    private Long paymentLowerBound;
    private Long paymentUpperBound;
    private String state;
    private Set<ProjectNewsDto> news;
}
