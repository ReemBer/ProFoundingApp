package com.itransition.profunding.controller;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.dto.ProjectDto;
import com.itransition.profunding.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/project/{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId) {
        return projectService.getFullProject(projectId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @PostMapping(value = "/project/create")
    public Project createProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto);
    }
}
