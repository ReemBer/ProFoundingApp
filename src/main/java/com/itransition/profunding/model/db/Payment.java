package com.itransition.profunding.model.db;

import lombok.*;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 05.09.2017 23:29
 */
@Entity
@Table(name = "payments")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_user")
    @IndexedEmbedded
    private User payerUser;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project rootProject;

    @Column(name = "amount")
    private Long amount;
}
