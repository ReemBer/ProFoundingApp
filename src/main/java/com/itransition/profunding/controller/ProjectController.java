package com.itransition.profunding.controller;

import com.itransition.profunding.exception.repository.ProjectSavingException;
import com.itransition.profunding.model.dto.ErrorInfoDto;
import com.itransition.profunding.model.dto.RatingDto;
import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.repository.fulltextSearch.FulltextRepository;
import com.itransition.profunding.service.FulltextSearchService;
import com.itransition.profunding.service.ProjectService;
import com.itransition.profunding.service.RatingService;
import com.itransition.profunding.service.TagService;
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
@RequestMapping(value = "/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TagService tagService;
    private final FulltextSearchService fulltextSearchService;

    @GetMapping(value = "/{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId) {
        return projectService.getFullProject(projectId);
    }

    @GetMapping(value = "/main_page")
    public Map<String, List<ProjectPreviewDto>> mainPageProjects() {
        return projectService.getMainPageProjects();
    }

    @GetMapping(value = "/success")
    public Map<String, Object> getSuccessProjectsNextPage() {
        return projectService.getSuccessProjectsNextPage();
    }

    @GetMapping(value = "/new")
    public Map<String, Object> getNewProjectsNextPage() {
        return projectService.getNewProjectsNextPage();
    }

    @GetMapping(value = "/tag/{tagName}")
    public Map<String, Object> getProjectsByTags(@PathVariable String tagName) {
        return tagService.findProjectsNextPageByTag(tagName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @PostMapping(value = "/create")
    public Boolean createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @PostMapping(value = "/update")
    public Boolean updateProject(@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(projectDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROOFED_USER')")
    @GetMapping(value = "/my")
    public List<ProjectDto> getMyProjects() {
        return projectService.getMyProjects();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/followed")
    public List<ProjectDto> getFollowedProjects() {
        return projectService.getMyFollowedProjects();
    }

    @GetMapping(value = "/search/{searchQuery}")
    public List<ProjectPreviewDto> fulltextSearch(@PathVariable String searchQuery) {
        return fulltextSearchService.search(searchQuery);
    }

    @ExceptionHandler(value = ProjectSavingException.class)
    public ErrorInfoDto badProjectSaving(HttpServletRequest request, Exception ex) {
        return new ErrorInfoDto(request.getRequestURL().toString(), ex);
    }
}
