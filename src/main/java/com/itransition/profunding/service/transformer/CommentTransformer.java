package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Comment;
import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 22:59
 */
@RequiredArgsConstructor
@Service
public class CommentTransformer implements Transformer<Comment, CommentDto> {
    @Override
    public CommentDto makeDto(Comment comment) {
        return new CommentDto();
    }

    @Override
    public Comment makeEntity(CommentDto commentDto) {
        return new Comment();
    }
}
