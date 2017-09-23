package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.RatingDto;
import com.itransition.profunding.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 23.09.2017 7:25
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/check/{projectId}")
    public Boolean checkRatingEnable(@PathVariable Long projectId) {
        return ratingService.checkEnable(projectId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rate")
    public RatingDto rate(@RequestBody RatingDto ratingDto) {
        return ratingService.rate(ratingDto);
    }
}
