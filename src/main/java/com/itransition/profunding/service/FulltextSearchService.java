package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.project.ProjectPreviewDto;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 25.09.2017 13:57
 */
public interface FulltextSearchService {
    List<ProjectPreviewDto> search(String query);
}
