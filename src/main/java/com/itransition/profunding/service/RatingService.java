package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.RatingDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 21:20
 */
public interface RatingService {
    RatingDto rate(RatingDto dto);
    Boolean checkEnable(Long projectId);
}
