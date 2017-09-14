package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.service.Transformer;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:45
 */
public class TagTransformer implements Transformer<Tag, TagDto> {
    @Override
    public TagDto makeDto(Tag tag) {
        return new TagDto();
    }

    @Override
    public Tag makeEntity(TagDto tagDto) {
        return new Tag();
    }
}
