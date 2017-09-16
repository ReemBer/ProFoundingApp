package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query("select p.subscribedUsers from Project p where p.id = :projectId")
    Set<User> findSubscribedUsers(@Param("projectId") Long projectId);
}
