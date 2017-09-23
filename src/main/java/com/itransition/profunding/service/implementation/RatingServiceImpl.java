package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.ProjectRating;
import com.itransition.profunding.model.db.RatingId;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.RatingDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.RatingRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.SecurityHelper;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.service.RatingService;
import com.itransition.profunding.service.transformer.RatingTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = userRepository.findOne(dto.getUserId());
        Project project = projectRepository.findOne(dto.getProjectId());
        RatingId ratingId = new RatingId(user, project);
        ProjectRating rating = new ProjectRating(ratingId, dto.getAmount());
        ratingRepository.save(rating);
        return ratingTransformer.buildDto(rating);
    }

    @Override
    public Boolean checkEnable(Long projectId) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityHelper.getAuthenticationWithCheck().getDetails();
        User user = userRepository.findOne(userDetails.getId());
        Project project = projectRepository.findOne(projectId);
        return ratingRepository.findOne(new RatingId(user, project)) == null;
    }
}
