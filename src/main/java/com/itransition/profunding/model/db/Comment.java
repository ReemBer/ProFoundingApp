package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "project"})
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "creation_date")
    private Date dateCreated;

    @Column(name = "content")
    @Type(type = "text")
    @Field
    private String content;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @IndexedEmbedded
    private User user;
}
