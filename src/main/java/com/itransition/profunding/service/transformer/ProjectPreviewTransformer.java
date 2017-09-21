package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.service.TransformerService;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        ProjectPreviewDto projectPreviewDto = modelMapper.map(entity, ProjectPreviewDto.class);
        projectPreviewDto.setLeftDays(setLeftDays(entity.getCompletionDate()));
        return projectPreviewDto;
    }

    private Long setLeftDays(Date completionDate) {
        Date timeNow = new Date();
        if (completionDate.before(timeNow)) return 0L;
        return (completionDate.getTime() - timeNow.getTime()) / 1000 / 60 / 60 / 24;
    }
}
