package com.itransition.profunding.controller;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.ProjectDto;
import com.itransition.profunding.repository.fulltextSearch.FulltextRepository;
import com.itransition.profunding.service.ProjectService;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 19:24
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final FulltextRepository fulltextRepository;
    private final Transformer<Project, ProjectDto> projectTransformer;

    @GetMapping(value = "/projects/{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId) {
        return projectService.getFullProject(projectId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @PostMapping(value = "/projects/create")
    public Project createProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto);
    }

    @GetMapping(value = "/projects/{searchQuery}/{offset}")
    public List<ProjectDto> fulltextSearch(@PathVariable String searchQuery, @PathVariable int offset) {
        return new LinkedList<>(projectTransformer.EntityToDtoSet(projectTransformer,
                fulltextRepository.fullTextSearch(searchQuery, offset)));
    }
}
