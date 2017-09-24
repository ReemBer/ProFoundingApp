package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.CommentDto;
import com.itransition.profunding.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create")
    public Boolean createComment(@RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/delete")
    public Boolean deleteComment(@RequestBody CommentDto commentDto) {
        return commentService.deleteComment(commentDto);
    }
}
