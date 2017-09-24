package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.CommentDto;

public interface CommentService {
    boolean saveComment(CommentDto commentDto);
    boolean deleteComment(CommentDto commentDto);
}
