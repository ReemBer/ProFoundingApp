package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Comment;
import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 22:59
 */
@Service
public class CommentTransformer extends TransformerService<Comment, CommentDto> {

    @Override
    public Comment parseDto(CommentDto dto) {
        return null;
    }

    @Override
    public CommentDto buildDto(Comment entity) {
        return modelMapper.map(entity, CommentDto.class);
    }
}
