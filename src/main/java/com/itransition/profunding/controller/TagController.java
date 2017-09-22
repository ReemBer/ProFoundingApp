package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "/all")
    public List<TagDto> getTags() {
        return  tagService.getAllTags();
    }

    @GetMapping(value = "/search/{tagName}")
    public Map<String, Object> findProjectsByTags(@PathVariable String tagName) {
        return tagService.findProjectsNextPageByTag(tagName);
    }
}
