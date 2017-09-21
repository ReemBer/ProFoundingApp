package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.repository.CommentRepository;
import com.itransition.profunding.service.CommentService;
import com.itransition.profunding.service.transformer.CommentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentTransformer commentTransformer;
    private final CommentRepository commentRepository;

    @Override
    public boolean saveComment(CommentDto commentDto) {
        return commentRepository.save(commentTransformer.parseDto(commentDto)) != null;
    }
}
