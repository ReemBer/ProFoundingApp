package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Payment;
import com.itransition.profunding.model.dto.PaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
