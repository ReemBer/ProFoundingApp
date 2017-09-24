package com.itransition.profunding.model.db;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;



/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 20:04
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRequestPk implements Serializable {

    private User user;

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
