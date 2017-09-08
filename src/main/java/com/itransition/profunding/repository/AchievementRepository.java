package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:09
 */
@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
