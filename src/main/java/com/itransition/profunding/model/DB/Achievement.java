package com.itransition.profunding.model.DB;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 06.09.2017 1:51
 */
@Entity
@Table(name = "achievements")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "achievement_id")
    private Long id;

    @ManyToMany(mappedBy = "achievements", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;
}
