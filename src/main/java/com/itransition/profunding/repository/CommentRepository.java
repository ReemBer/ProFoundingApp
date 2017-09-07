package com.itransition.profunding.repository;

import com.itransition.profunding.model.DB.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:08
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
