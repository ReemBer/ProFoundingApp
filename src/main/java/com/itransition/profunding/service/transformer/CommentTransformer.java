package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Comment;
import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentTransformer extends TransformerService<Comment, CommentDto> {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public Comment parseDto(CommentDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setProject(projectRepository.findOne(dto.getProjectId()));
        comment.setUser(userRepository.findOne(dto.getUser().getId()));
        return comment;
    }

    @Override
    public CommentDto buildDto(Comment entity) {
        return modelMapper.map(entity, CommentDto.class);
    }
}
