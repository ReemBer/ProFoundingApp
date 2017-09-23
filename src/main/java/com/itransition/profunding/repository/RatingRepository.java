package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.ProjectRating;
import com.itransition.profunding.model.db.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 22.09.2017 21:25
 */
public interface RatingRepository extends JpaRepository<ProjectRating, RatingId> {
}
