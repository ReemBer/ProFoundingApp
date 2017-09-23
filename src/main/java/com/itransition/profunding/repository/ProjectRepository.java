package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.*;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:07
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    Project findOne(Long aLong);

    List<Project> findAllByCreatorUserId(Long id);

    Page<Project> findAllByOrderByIdDesc(Pageable pageable);

    Page<Project> findAllByStatusOrderByIdDesc(ProjectStatus status, Pageable pageable);

    Page<Project> findAllByTagsOrderByIdDesc(Set<Tag> tags, Pageable pageable);

    @Query("select p.ratings from Project p where p.id = :idParam")
    List<ProjectRating> findProjectRatingsById(@Param("idParam") Long idParam);
}
