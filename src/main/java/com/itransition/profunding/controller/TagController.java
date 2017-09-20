package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "/tags/all")
    public List<TagDto> getTags() {
        return  tagService.getAllTags();
    }
}
