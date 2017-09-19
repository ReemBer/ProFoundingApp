package com.itransition.profunding.service.implementation;

import com.itransition.profunding.exception.repository.ProjectSavingException;
import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.ProjectStatus;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.project.ProjectDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.SecurityHelper;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.service.ProjectService;
import com.itransition.profunding.service.transformer.ProjectPreviewTransformer;
import com.itransition.profunding.service.transformer.ProjectTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:09
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectTransformer projectTransformer;
    private final UserRepository userRepository;
    private final ProjectPreviewTransformer projectPreviewTransformer;

    @Override
    public ProjectDto getFullProject(Long id) {
        return projectTransformer.buildDto(projectRepository.findOne(id));
    }

    @Override
    public List<ProjectDto> getMyProjects() {
        JwtUserDetails userDetails = ((JwtUserDetails)SecurityHelper.getAuthenticationWithCheck().getDetails());
        List<Project> projects = projectRepository.findAllByCreatorUserId(userDetails.getId());
        return projectTransformer.buildDtoList(projects);
    }

    @Override
    public List<ProjectDto> getMyFollowedProjects() {
        JwtUserDetails userDetails = ((JwtUserDetails) SecurityHelper.getAuthenticationWithCheck().getDetails());
        User user = userRepository.getOne(userDetails.getId());
        return projectTransformer.buildDtoList(new LinkedList<>(user.getFollowedProjects()));
    }

    @Override
    public Boolean createProject(ProjectDto projectDto) {
        boolean success = projectRepository.save(projectTransformer.parseDto(projectDto)) != null;
        if (!success) {
            throw new ProjectSavingException("Error through saving Project to database.");
        }
        return true;
    }

    @Override
    public Map<String, List<ProjectPreviewDto>> getMainPageProjects() {
        Map<String, List<ProjectPreviewDto> > result = new HashMap<>();
        List<Project> successfulProjects = projectRepository.findAllByStatusOrderByIdDesc(ProjectStatus.PROFITED);
        List<Project> newProjects = projectRepository.findAllByOrderByIdDesc();
        result.put("successProjects", projectPreviewTransformer.buildDtoList(successfulProjects));
        result.put("newProjects", projectPreviewTransformer.buildDtoList(newProjects));
        return result;
    }
}
