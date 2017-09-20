package com.itransition.profunding.controller;

import com.itransition.profunding.exception.repository.ProjectSavingException;
import com.itransition.profunding.model.dto.ErrorInfoDto;
import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.repository.fulltextSearch.FulltextRepository;
import com.itransition.profunding.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/projects/{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId) {
        return projectService.getFullProject(projectId);
    }

    @GetMapping(value = "/projects/main_page")
    public Map<String, List<ProjectPreviewDto>> mainPageProjects() {
        return projectService.getMainPageProjects();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @PostMapping(value = "/projects/create")
    public Boolean createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @GetMapping(value = "/projects/my")
    public List<ProjectDto> getMyProjects() {
        return projectService.getMyProjects();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/projects/followed")
    public List<ProjectDto> getFollowedProjects() {
        return projectService.getMyFollowedProjects();
    }

    @GetMapping(value = "/projects/{searchQuery}/{offset}")
    public List<ProjectDto> fulltextSearch(@PathVariable String searchQuery, @PathVariable int offset) {
        return null;
    }

    @ExceptionHandler(value = ProjectSavingException.class)
    public ErrorInfoDto badProjectSaving(HttpServletRequest request, Exception ex) {
        return new ErrorInfoDto(request.getRequestURL().toString(), ex);
    }
}
