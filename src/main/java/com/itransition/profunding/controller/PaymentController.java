package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.PaymentDto;
import com.itransition.profunding.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/payment/add")
    public Long addPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.addPayment(paymentDto);
    }
}
