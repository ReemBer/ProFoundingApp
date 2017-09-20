package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.repository.TagRepository;
import com.itransition.profunding.service.TagService;
import com.itransition.profunding.service.transformer.TagTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private final TagTransformer tagTransformer;

    @Override
    public List<TagDto> getAllTags() {
       List<Tag> tags = tagRepository.findAll();
       return tagTransformer.buildDtoList(tags);
    }
}
