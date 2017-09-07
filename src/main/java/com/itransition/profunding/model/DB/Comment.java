package com.itransition.profunding.model.DB;

import lombok.*;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 06.09.2017 2:30
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_author")
    @IndexedEmbedded
    private User userAuthor;

    @ManyToOne
    @JoinColumn(name = "root_project")
    @IndexedEmbedded
    private Project rootProject;

    @Column(name = "text")
    private String text;
}
