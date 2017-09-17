package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.model.dto.TagDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 17.09.2017 6:38
 */
@Component
@RequiredArgsConstructor
public class TagTransformer extends TransformerService<Tag, TagDto> {

    private final ModelMapper parser;
    private final ModelMapper builder;
    private boolean parserInitialized = false;
    private boolean builderInitialized = false;

    @Override
    public Tag parseDto(TagDto dto) {
        if (!parserInitialized) {
            parser.addMappings(getPropertiesForParsing());
            parserInitialized = true;
        }
        return parser.map(dto, Tag.class);
    }

    @Override
    public TagDto buildDto(Tag entity) {
        if (!builderInitialized) {
            builder.addMappings(getPropertiesForBuilding());
            builderInitialized = true;
        }
        return builder.map(entity, TagDto.class);
    }

    private PropertyMap<TagDto, Tag> getPropertiesForParsing() {
        return new PropertyMap<TagDto, Tag>() {
            @Override
            protected void configure() {
                map().setProjects(null);
                map().setTagName(source.getValue());
            }
        };
    }

    private PropertyMap<Tag, TagDto> getPropertiesForBuilding() {
        return new PropertyMap<Tag, TagDto>() {
            @Override
            protected void configure() {
                map().setValue(source.getTagName());
            }
        };
    }
}
