package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.Tag;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 21:45
 */
@Service
@RequiredArgsConstructor
public class TagTransformer implements Transformer<Tag, String> {
    @Override
    public String makeDto(Tag tag) {
        return "";
    }
        @Override
    public Tag makeEntity(String tagDto) {
        throw new UnsupportedOperationException();
    }
}
