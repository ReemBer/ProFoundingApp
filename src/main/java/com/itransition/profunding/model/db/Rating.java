package com.itransition.profunding.model.db;

import lombok.*;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 3:20
 */
@Entity
@Table(name = "ratings")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project rootProject;
}
