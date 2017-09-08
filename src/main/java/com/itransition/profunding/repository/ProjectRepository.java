package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:07
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
