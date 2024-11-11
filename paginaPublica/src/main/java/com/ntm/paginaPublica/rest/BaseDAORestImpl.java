package com.ntm.paginaPublica.rest;


import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service
public abstract class BaseDAORestImpl<E> implements BaseDAORest<E> {

    @Autowired
    protected RestTemplate restTemplate;


    public List<E> listar(Class<E[]> clase) throws ErrorDAOException {

        try {

            String uri= getUri("LISTAR");

            ResponseEntity<E[]> response = restTemplate.getForEntity(uri, clase);
            E[] entitys = response.getBody();
            List<E> m = Arrays.asList(entitys);

            return  m;

        } catch (Exception ex){
            throw new ErrorDAOException("Error de Sistemas");
        }
    }
}
