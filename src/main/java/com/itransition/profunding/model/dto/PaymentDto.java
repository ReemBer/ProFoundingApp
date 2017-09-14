package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PaymentDto {
    private Long userId;
    private Long projectId;
    private Long amount;
}
