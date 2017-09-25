package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 05.09.2017 21:59
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"projects", "followedProjects", "comments"})
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @Field
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "image")
    private String image;

    @Column(name = "last_login_date")
    private String date;

    @Column
    private Boolean isSendConfirm;

    @Column
    private Boolean isBlocked;

    @OneToMany(mappedBy = "creatorUser",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projects;

    @ManyToMany(mappedBy = "subscribedUsers",cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Project> followedProjects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectRating> ratings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
