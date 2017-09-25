package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.PaymentDto;

public interface PaymentService {
    Long addPayment(PaymentDto dto);
}
