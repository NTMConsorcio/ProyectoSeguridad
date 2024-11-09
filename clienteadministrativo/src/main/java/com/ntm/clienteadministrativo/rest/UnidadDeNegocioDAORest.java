package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UnidadDeNegocioDAORest extends BaseDAORestImpl<UnidadDeNegocioDTO, String>{

    private final RestTemplate restTemplate;
    public UnidadDeNegocioDAORest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getUri(String caso) throws ErrorServiceException{
        return "http://localhost:9000/api/v1/unidadDeNegocio";
    }
    public List<UnidadDeNegocioDTO> getActivos() throws ErrorServiceException {
        try {
            String uri = "http://localhost:9000/api/v1/unidadDeNegocio/activos";
            ResponseEntity<UnidadDeNegocioDTO[]> response = restTemplate.getForEntity(uri, UnidadDeNegocioDTO[].class);

            // Verifica si la respuesta es exitosa
            if (response.getStatusCode() == HttpStatus.OK) {
                UnidadDeNegocioDTO[] unidadesArray = response.getBody();
                return Arrays.asList(unidadesArray); // Convierte el array a una lista

            } else {
                throw new ErrorServiceException("Error al obtener las unidades activas: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }

    }


}
