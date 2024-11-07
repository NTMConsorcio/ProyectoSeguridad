package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.BaseDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;

import java.io.Serializable;
import java.util.List;

public interface BaseDAORest <E extends BaseDTO, ID extends Serializable> {
    void crear(Class<E> clase, E entity) throws ErrorServiceException;
    void actualizar(E entity) throws ErrorServiceException;
    void eliminar(ID id) throws ErrorServiceException;
    E buscar(Class<E> clase, ID id) throws ErrorServiceException;
    List<E> listar(Class<E[]> clase) throws ErrorServiceException;
    String getUri(String caso) throws ErrorServiceException;
}
