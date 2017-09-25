package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Payment;
import com.itransition.profunding.model.dto.PaymentDto;
import com.itransition.profunding.repository.PaymentRepository;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.service.PaymentService;
import com.itransition.profunding.service.transformer.PaymentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentTransformer paymentTransformer;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Long addPayment(PaymentDto dto) {
        Payment payment = paymentTransformer.parseDto(dto);
        Payment check = paymentRepository.save(payment);
        Long currentSum = payment.getProject().getCurrentSum();
        if (check != null) {
            currentSum += dto.getAmount();
            projectRepository.setCurrentSumById(currentSum, dto.getProjectId());
        }
        return currentSum;
    }
}
