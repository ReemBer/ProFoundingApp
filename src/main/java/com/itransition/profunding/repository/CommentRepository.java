package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:08
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
