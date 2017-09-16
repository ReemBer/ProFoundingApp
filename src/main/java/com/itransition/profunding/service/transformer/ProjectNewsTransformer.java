package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.ProjectNews;
import com.itransition.profunding.model.dto.ProjectNewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 15.09.2017 22:28
 */
@Component
@RequiredArgsConstructor
public class ProjectNewsTransformer {

    public ProjectNewsDto makeDto(ProjectNews projectNews) {
        return null;
    }

    public ProjectNews makeEntity(ProjectNewsDto projectNewsDto) {
        return null;
    }
}
