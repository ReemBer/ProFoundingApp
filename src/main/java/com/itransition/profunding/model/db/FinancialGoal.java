package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 06.09.2017 3:01
 */
@Entity
@Table(name = "financial_goals")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "rootProject"})
@NoArgsConstructor
@AllArgsConstructor
public class FinancialGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "financial_goal_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "root_project")
    private Project rootProject;

    @Column(name = "title")
    @Field
    private String title;

    @Column(name = "cost")
    private Long cost;

}
