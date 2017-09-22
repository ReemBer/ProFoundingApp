package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;

import java.util.List;
import java.util.Map;

public interface TagService {
    List<TagDto> getAllTags();
    Map<String, Object> findProjectsNextPageByTag(String tagName);
}
