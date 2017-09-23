package com.itransition.profunding.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 19:51
 */
@Entity
@Table(name = "project_ratings")
@AssociationOverrides({
        @AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "id.project", joinColumns = @JoinColumn(name = "project_id"))
})
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRating {

    private RatingId id;

    @Column(name = "amount")
    private int amount;

    @EmbeddedId
    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    @Transient
    public User getUser() {
        return this.id.getUser();
    }

    @Transient
    public Project getProject() {
        return this.id.getProject();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }
    public void setProject(Project project) {
        this.id.setProject(project);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
