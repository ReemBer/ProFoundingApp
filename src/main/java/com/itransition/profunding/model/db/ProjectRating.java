package com.itransition.profunding.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 19:51
 */
@Entity
@Table(name = "project_ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "amount")
    private int amount;
}
