package com.itransition.profunding.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 16.09.2017 15:43
 */
@Service
public abstract class TransformerService<E, D> {

    @Autowired
    @JsonIgnore
    protected ModelMapper modelMapper;

    abstract public E parseDto(D dto);

    abstract public D buildDto(E entity);

    public List<E> parseDtoList(List<D> dtoList) {
        List<E> entityList = new LinkedList<>();
        for (D dto : dtoList) {
            entityList.add(parseDto(dto));
        }
        return entityList;
    }

    public List<D> buildDtoList(List<E> entityList) {
        List<D> dtoList = new LinkedList<>();
        for (E entity : entityList) {
            dtoList.add(buildDto(entity));
        }
        return dtoList;
    }
}
