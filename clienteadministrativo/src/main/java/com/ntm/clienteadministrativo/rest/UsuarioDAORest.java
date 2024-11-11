package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDAORest  extends BaseDAORestImpl<UsuarioDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/usuario";
    }

    public void registrar(UsuarioDTO usuario) throws ErrorServiceException {
        try {
            String uri = "http://localhost:9000/api/v1/usuario/crear";
            restTemplate.postForEntity(uri, usuario, UsuarioDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public UsuarioDTO buscarCuenta(String cuenta)  throws ErrorServiceException {

        try {
            String uriAux = getUri("BUSCAR");
            String uri = uriAux + "/buscar/" + cuenta;

            ResponseEntity<UsuarioDTO> response = restTemplate.getForEntity(uri, UsuarioDTO.class);
            UsuarioDTO entity = response.getBody();
            return entity;

        } catch (Exception ex){
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
