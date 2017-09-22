package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:05
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTagName(String tagName);

    @Query("select t.projects from Tag t where t.tagName = :tagNameParam order by t.id desc")
    Page<Project> findTagedProjectsOrderByIdDesc(@Param("tagNameParam") String tagNameParam, Pageable pageable);
}
