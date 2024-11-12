package com.ntm.clienteadministrativo.rest;

import com.ntm.clienteadministrativo.dto.HabitanteDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;


@Service
public class HabitanteDAORest  extends BaseDAORestImpl<HabitanteDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://localhost:9000/api/v1/habitante";
    }

    public void registrar(HabitanteDTO habitante) throws ErrorServiceException {
        try {
            String uri = "http://localhost:9000/api/v1/habitante/crear";
            restTemplate.postForEntity(uri, habitante, HabitanteDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
