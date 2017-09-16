package com.itransition.profunding.model.db;

import lombok.*;

import javax.persistence.*;
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
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "proofing_status")
    @Enumerated(EnumType.STRING)
    private ProofingStatus proofingStatus;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "last_login_date")
    private String date;

    @ManyToMany(mappedBy = "subscribedUsers",cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Project> projectSubscribes;

    @OneToMany(mappedBy = "payerUser",cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "creatorUser",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> myProjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "users_achievements", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private Set<Achievement> achievements;

    @OneToMany(mappedBy = "userAuthor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_project_news", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_news_id"))
    private Set<ProjectNews> projectNews;
}
