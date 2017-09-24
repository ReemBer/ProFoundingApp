package com.itransition.profunding.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 19:12
 */
@Entity
@Table(name = "confirm_requests")
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRequest {

    private ConfirmRequestPk pk;

    @Column
    private String scanOfPassport;

    @EmbeddedId
    public ConfirmRequestPk getPk() {
        return pk;
    }

    public void setPk(ConfirmRequestPk pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return this.pk.getUser();
    }

    public void setUser(User user) {
        this.pk.setUser(user);
    }

    public String getScanOfPassport() {
        return scanOfPassport;
    }

    public void setScanOfPassport(String scanOfPassport) {
        this.scanOfPassport = scanOfPassport;
    }
}
