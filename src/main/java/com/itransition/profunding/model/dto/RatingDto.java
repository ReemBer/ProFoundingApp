package com.itransition.profunding.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 20:16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RatingDto {
    private Long userId;
    private Long projectId;
    private Integer amount;
    private Double totalRating;
}
