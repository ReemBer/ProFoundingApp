package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 1:47
 */
@Entity
@Table(name = "tags")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"projects"})
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    private Set<Project> projects;

    @Column(name = "tag_name")
    @Field
    private String tagName;

}
