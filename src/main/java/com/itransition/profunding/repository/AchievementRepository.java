package com.itransition.profunding.repository;

import com.itransition.profunding.model.DB.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:09
 */
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
