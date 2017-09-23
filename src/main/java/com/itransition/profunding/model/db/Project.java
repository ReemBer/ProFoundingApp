package com.itransition.profunding.model.db;

import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
@EqualsAndHashCode(exclude = {"financialGoals", "subscribedUsers", "tags", "comments"})
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "title")
    @Field
    private String title;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "description")
    @Type(type = "text")
    @Field
    private String description;

    @Column(name = "content")
    @Type(type = "text")
    @Field
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    @Column(name = "current_sum")
    private Long currentSum;

    @OneToMany(mappedBy = "rootProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @IndexedEmbedded
    private Set<FinancialGoal> financialGoals;

    @Column(name = "total_cost")
    private Long totalCost;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    private User creatorUser;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(name = "project_user_subscribes", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> subscribedUsers;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "projects_tags", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @IndexedEmbedded
    private Set<Tag> tags;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectRating> ratings;
}
