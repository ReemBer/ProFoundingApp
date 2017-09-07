package com.itransition.profunding.model.DB;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 06.09.2017 3:01
 */
@Entity
@Table(name = "financial_goals")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FinancialGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "financial_goal_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project rootProject;

    @Column(name = "target_amount")
    private Long targetAmount;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline_date")
    private Date deadlineDate;

    @Column(name = "financial_goal_status")
    @Enumerated(EnumType.STRING)
    private FinancialGoalStatus financialGoalStatus;
}
