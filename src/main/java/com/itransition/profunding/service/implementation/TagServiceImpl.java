package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Project;
import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.model.dto.project.ProjectPreviewDto;
import com.itransition.profunding.repository.ProjectRepository;
import com.itransition.profunding.repository.TagRepository;
import com.itransition.profunding.service.TagService;
import com.itransition.profunding.service.transformer.ProjectTransformer;
import com.itransition.profunding.service.transformer.TagTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static com.itransition.profunding.util.AppConstants.PAGE_SIZE;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final ProjectRepository projectRepository;
    private final ProjectTransformer projectTransformer;
    private final TagRepository tagRepository;
    private final TagTransformer tagTransformer;

    Pageable projectsPageable = new PageRequest(0, PAGE_SIZE);
    Page<Project> projectsPage;
    @Override
    public List<TagDto> getAllTags() {
       List<Tag> tags = tagRepository.findAll();
       return tagTransformer.buildDtoList(tags);
    }

    @Override
    public Map<String, Object> findProjectsNextPageByTag(String tagName) {
        Map<String, Object> result = new HashMap<>();
        Set<Tag> tags = prepareTags(tagName);
        projectsPage = projectRepository.findAllByTagsOrderByIdDesc(tags, projectsPageable);
        projectsPageable = chooseNextPage(projectsPage, result, projectsPageable);
        result.put("page", projectsPage.getContent());
        return result;
    }

    private Set<Tag> prepareTags(String tagName) {
        Tag searchedTag = tagRepository.findByTagName(tagName);
        Set<Tag> tags = new HashSet<>();
        tags.add(searchedTag);
        return tags;
    }

    private Pageable chooseNextPage(Page<Project> projectsPage, Map<String, Object> result, Pageable pageable) {
        return isLastPageCheck(projectsPage, result) ? pageable.first() : pageable.next();
    }

    private boolean isLastPageCheck(Page<Project> page, Map<String, Object> result) {
        result.put("last", !page.hasNext());
        return !page.hasNext();
    }
}
