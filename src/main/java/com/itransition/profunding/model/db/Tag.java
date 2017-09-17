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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Project> projects;

    @Column(name = "tag_name", unique = true)
    @Field
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if(o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        return this.tagName.equals(((Tag) o).tagName);
    }
}
