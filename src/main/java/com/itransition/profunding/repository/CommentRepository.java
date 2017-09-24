package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    int deleteCommentByProject_IdAndContentAndUser_IdAndDateCreated(
            Long projectId, String content, Long userId, Date dateCreated
    );
}
