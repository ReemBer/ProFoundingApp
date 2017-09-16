package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Comment;
import com.itransition.profunding.model.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 22:59
 */
@Component
@RequiredArgsConstructor
public class CommentTransformer{

    public CommentDto makeDto(Comment comment) {
        return new CommentDto();
    }

    public Comment makeEntity(CommentDto commentDto) {
        return new Comment();
    }
}
