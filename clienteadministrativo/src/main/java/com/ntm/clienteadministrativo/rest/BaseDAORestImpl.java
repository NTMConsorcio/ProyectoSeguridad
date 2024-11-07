package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.BaseDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service
public abstract class BaseDAORestImpl<E extends BaseDTO, ID extends Serializable> implements BaseDAORest<E, ID> {

    @Autowired
    protected RestTemplate restTemplate;

    public void crear(Class<E> clase, E entity) throws ErrorServiceException {

        try {

            String uri = getUri("CREAR");
            restTemplate.postForEntity(uri, entity, clase);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void actualizar(E entity) throws ErrorServiceException {

        try {
            String uriAux = getUri("ACTUALIZAR");
            String uri = uriAux + "/" + entity.getId();
            restTemplate.put(uri, entity);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void eliminar(ID id) throws ErrorServiceException {

        try {
            String uriAux = getUri("ELIMINAR");
            String uri = uriAux + "/" + id;
            restTemplate.delete(uri);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public E buscar(Class<E> clase, ID id)  throws ErrorServiceException {

        try {
            String uriAux = getUri("BUSCAR");
            String uri = uriAux + "/" + id;

            ResponseEntity<E> response = restTemplate.getForEntity(uri, clase);
            E entity = response.getBody();

            return entity;

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public List<E> listar(Class<E[]> clase) throws ErrorServiceException {

        try {

            String uri= getUri("LISTAR");

            ResponseEntity<E[]> response = restTemplate.getForEntity(uri, clase);
            E[] entitys = response.getBody();
            List<E> m = Arrays.asList(entitys);

            return  m;

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
