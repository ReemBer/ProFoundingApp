package com.itransition.profunding.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 11.09.2017 0:11
 */

public interface Transformer<Entity, Dto> {
    Dto makeDto(Entity entity);

    Entity makeEntity(Dto dto);

    default <Entity1, Dto1> Set<Dto1> EntityToDtoSet(Transformer<Entity1, Dto1> transformer,
                                                     Collection<Entity1> entities) {
        Set<Dto1> dtos = new HashSet<>();
        if (entities != null) {
            for (Entity1 entity : entities) {
                dtos.add(transformer.makeDto(entity));
            }
        }
        return dtos;
    }

    default <Entity1, Dto1> Set<Entity1> DtoToEntitySet(Transformer<Entity1, Dto1> transformer, Collection<Dto1> dtos) {
        Set<Entity1> entitys = new HashSet<>();
        if(dtos != null) {
            for (Dto1 dto : dtos) {
                entitys.add(transformer.makeEntity(dto));
            }
        }
        return entitys;
    }
}
