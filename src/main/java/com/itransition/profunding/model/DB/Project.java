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

    @Column(name = "project_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_financial_goal")
    private FinancialGoal mainFinancialGoal;

    @Column(name = "description")
    private String description;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    @IndexedEmbedded
    private User creatorUser;

    @ManyToMany(mappedBy = "projectSubscribes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> subscribedUsers;

    @OneToMany(mappedBy = "rootProject",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FinancialGoal> additionalFinancialGoals;

    @Column(name = "current_amount")
    private Long currentAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_tags", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "rootProject", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @Column(name = "total_rating")
    private Float totalRating;

    @OneToMany(mappedBy = "rootProject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "rootProject", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    @Column(name = "payment_lower_bound")
    private Long paymentLowerBound;

    @Column(name = "payment_upper_bound")
    private Long paymentUpperBound;

    @Column(name = "project_current_state")
    @Enumerated(value = EnumType.STRING)
    private ProjectCurrentState projectCurrentState;

    @OneToMany(mappedBy = "rootProject",fetch = FetchType.LAZY)
    private Set<ProjectNews> projectNews;
}
