package com.ntm.clienteadministrativo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntm.clienteadministrativo.dto.ContactoTelefonicoDTO;
import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthDAORest {

    @Autowired
    private RestTemplate restTemplate;

    /*
    public String authenticate(UsuarioDTO usuario) throws ErrorServiceException {
        try {
            String uri = "http://appseguridad:9000/auth/login";
            System.out.println(uri);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, usuario, String.class);
            String token = response.getBody();
            System.out.println(token);
            return token;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error al autenticar");
        }
    }
    */
    public String authenticate(UsuarioDTO usuario) throws ErrorServiceException {
        try {

            String uri = "http://appseguridad:9000/auth/login";
            HttpHeaders headers = new HttpHeaders();
            headers.add("No-Token", "true");
            headers.setContentType(MediaType.APPLICATION_JSON); // Asumimos que la API espera JSON

            String usuarioJson = new ObjectMapper().writeValueAsString(usuario);

            HttpEntity<String> entity = new HttpEntity<>(usuarioJson, headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            String token = response.getBody();
            return token;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error al autenticar");
        }
    }


}
