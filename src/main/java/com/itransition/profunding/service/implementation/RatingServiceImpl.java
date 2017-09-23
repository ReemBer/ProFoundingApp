package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.ProjectRating;
import com.itransition.profunding.model.dto.RatingDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.RatingRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.RatingService;
import com.itransition.profunding.service.transformer.RatingTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 21:21
 */
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final ProjectRepository projectRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RatingTransformer ratingTransformer;

    @Override
    public RatingDto rate(RatingDto dto) {
        ProjectRating rating = new ProjectRating();
        rating.setProject(projectRepository.getOne(dto.getProjectId()));
        rating.setUser(userRepository.getOne(dto.getUserId()));
        rating.setAmount(dto.getAmount());
        rating.getUser().getRatings().add(rating);
        rating.getProject().getRatings().add(rating);
        ratingRepository.save(rating);
        return ratingTransformer.buildDto(rating);
    }
}
