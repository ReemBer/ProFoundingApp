package com.itransition.profunding.model.DB;

import lombok.*;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 05.09.2017 23:24
 */
@Entity
@Table(name = "projects")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    @IndexedEmbedded
    private User creatorUser;

    @ManyToMany(mappedBy = "projectSubscribes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> subscribedUsers;
}
