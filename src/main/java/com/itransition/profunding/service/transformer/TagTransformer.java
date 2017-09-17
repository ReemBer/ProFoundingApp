package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 17.09.2017 6:38
 */
@Component
@RequiredArgsConstructor
public class TagTransformer extends TransformerService<Tag, TagDto> {

    private final ModelMapper modelMapper;

    @Override
    public Tag parseDto(TagDto dto) {
        return modelMapper.map(dto, Tag.class);
    }

    @Override
    public TagDto buildDto(Tag entity) {
        return modelMapper.map(entity, TagDto.class);
    }
}
