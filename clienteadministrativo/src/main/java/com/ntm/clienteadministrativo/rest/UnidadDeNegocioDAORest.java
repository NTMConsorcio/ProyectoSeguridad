package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.UnidadDeNegocioDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public List<UnidadDeNegocioDTO> getActivos()  {
        List<UnidadDeNegocioDTO> unidades = restTemplate.getForObject(
                "http://localhost:9000/api/v1/unidadDeNegocio/activos",
                List.class
        );
        return unidades;
    }


}
