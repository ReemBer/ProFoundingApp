package com.itransition.profunding.repository;

import com.itransition.profunding.model.DB.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:07
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
