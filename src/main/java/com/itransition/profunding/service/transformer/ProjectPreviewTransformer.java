package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.service.TransformerService;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 19.09.2017 21:38
 */
@Component
public class ProjectPreviewTransformer extends TransformerService<Project, ProjectPreviewDto> {
    @Override
    public Project parseDto(ProjectPreviewDto dto) {
        return modelMapper.map(dto, Project.class);
    }

    @Override
    public ProjectPreviewDto buildDto(Project entity) {
        return modelMapper.map(entity, ProjectPreviewDto.class);
    }
}
