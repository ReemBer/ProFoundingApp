package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Comment;
import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.repository.CommentRepository;
import com.itransition.profunding.service.CommentService;
import com.itransition.profunding.service.transformer.CommentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentTransformer commentTransformer;
    private final CommentRepository commentRepository;

    @Override
    public boolean saveComment(CommentDto commentDto) {
        Comment check = commentRepository.save(commentTransformer.parseDto(commentDto));
        return check != null;
    }

    @Override
    @Transactional
    public boolean deleteComment(CommentDto commentDto) {
        return commentRepository.deleteCommentByProject_IdAndContentAndUser_IdAndDateCreated(
                commentDto.getProjectId(), commentDto.getContent(), commentDto.getUser().getId(),
                commentDto.getDateCreated()
        ) != 0;
    }
}
