package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Payment;
import com.itransition.profunding.model.dto.PaymentDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTransformer extends TransformerService<Payment, PaymentDto> {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Payment parseDto(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setUser(userRepository.findOne(dto.getUserId()));
        payment.setProject(projectRepository.findOne(dto.getProjectId()));
        return payment;
    }

    @Override
    public PaymentDto buildDto(Payment entity) {
        return modelMapper.map(entity, PaymentDto.class);
    }
}
