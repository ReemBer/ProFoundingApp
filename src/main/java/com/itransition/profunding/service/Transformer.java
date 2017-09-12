package com.itransition.profunding.service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 11.09.2017 0:11
 */

public interface Transformer<Entity, Dto> {
    Dto makeDto(Entity entity);
    Entity makeEntity(Dto dto);
}
