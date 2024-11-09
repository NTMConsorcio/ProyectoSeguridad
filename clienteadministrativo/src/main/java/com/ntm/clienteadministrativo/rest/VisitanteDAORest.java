package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.dto.VisitanteDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VisitanteDAORest extends BaseDAORestImpl<VisitanteDTO,String> {
    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/visitante";
    }

    public List<VisitanteDTO> getActivos() throws ErrorServiceException {
        try {
            String uri = "http://localhost:9000/api/v1/visitante/activos";
            ResponseEntity<VisitanteDTO[]> response = restTemplate.getForEntity(uri, VisitanteDTO[].class);

            // Verifica si la respuesta es exitosa
            if (response.getStatusCode() == HttpStatus.OK) {
                VisitanteDTO[] visitantesArray = response.getBody();
                return Arrays.asList(visitantesArray); // Convierte el array a una lista

            } else {
                throw new ErrorServiceException("Error al obtener los visitantes activos: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }

    }
}
