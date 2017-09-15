package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.type.TextType;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "project_title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private TextType content;

    @Column(name = "image")
    private String image;

    @Column(name = "completion_date")
    private Date completionDate;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    @IndexedEmbedded
    private User creatorUser;

//    @ManyToMany(mappedBy = "projectSubscribes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<User> subscribedUsers;

    @OneToMany(mappedBy = "rootProject",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FinancialGoal> financialGoals;

    @Column(name = "total_amount")
    private Long totalAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_tags", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

//    @OneToMany(mappedBy = "rootProject", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private Set<Comment> comments;

    @Column(name = "total_rating")
    private Long totalRating;

//    @OneToMany(mappedBy = "rootProject", fetch = FetchType.LAZY)
//    private Set<Payment> payments;


//    @Column(name = "project_current_state")
//    @Enumerated(value = EnumType.STRING)
//    private ProjectCurrentState projectCurrentState;

//    @OneToMany(mappedBy = "rootProject",fetch = FetchType.LAZY)
//    private Set<ProjectNews> projectNews;
}
