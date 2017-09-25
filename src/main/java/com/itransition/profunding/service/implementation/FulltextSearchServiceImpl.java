package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.repository.fulltextSearch.FulltextRepository;
import com.itransition.profunding.service.FulltextSearchService;
import com.itransition.profunding.service.transformer.ProjectPreviewTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 25.09.2017 13:59
 */
@Service
@RequiredArgsConstructor
public class FulltextSearchServiceImpl implements FulltextSearchService {

    private final FulltextRepository fulltextRepository;
    private final ProjectPreviewTransformer projectPreviewTransformer;

    @Override
    public List<ProjectPreviewDto> search(String query) {
        return projectPreviewTransformer.buildDtoList(fulltextRepository.fullTextSearch(query));
    }
}
