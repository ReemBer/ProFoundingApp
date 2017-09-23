package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.ProjectRating;
import com.itransition.profunding.model.dto.RatingDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 20:49
 */
@Service
@RequiredArgsConstructor
public class RatingTransformer extends TransformerService<ProjectRating, RatingDto> {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectRating parseDto(RatingDto dto) {
        ProjectRating rating = new ProjectRating();
        rating.setAmount(dto.getAmount());
        rating.setUser(userRepository.findOne(dto.getUserId()));
        rating.setProject(projectRepository.findOne(dto.getProjectId()));
        return rating;
    }

    @Override
    public RatingDto buildDto(ProjectRating entity) {
        RatingDto dto = new RatingDto();
        List<ProjectRating> projectRatings = projectRepository.findProjectRatingsById(entity.getProject().getId());
        dto.setTotalRating(calculateTotalRating(projectRatings));
        return dto;
    }

    private double calculateTotalRating(List<ProjectRating> ratings) {
        double result = 0.;
        for(ProjectRating rating : ratings) {
            result += rating.getAmount();
        }
        result /= ratings.size();
        return result;
    }
}
