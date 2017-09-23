package com.itransition.profunding.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 23.09.2017 17:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RatingId implements Serializable {

    private User user;
    private Project project;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
