package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends Base, ID extends Serializable>{
    public List<E> findAll() throws Exception;
    public <D> D convertToDto(E entity);
    public <D> List<D> convertToDtoList(List<E> entities);;
    public Page<E> findAll(Pageable pageable) throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id,E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
    public boolean validar(E entity, String caso) throws Exception;
}
