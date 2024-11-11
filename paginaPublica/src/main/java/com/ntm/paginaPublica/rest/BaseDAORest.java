package com.ntm.paginaPublica.rest;

import com.ntm.paginaPublica.rest.error.ErrorDAOException;

import java.util.List;

public interface BaseDAORest<E> {
    List<E> listar(Class<E[]> clase) throws ErrorDAOException;
    String getUri(String caso) throws ErrorDAOException;
}
