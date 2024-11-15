package com.ntm.clienteadministrativo.rest;


import com.ntm.clienteadministrativo.dto.PlanillaHorariaDTO;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class PlanillaHorariaDAORest  extends BaseDAORestImpl<PlanillaHorariaDTO, String> {

    @Override
    public String getUri(String caso) throws ErrorServiceException {
        return "http://appseguridad:9000/api/v1/planilla";
    }

    public void registrar(PlanillaHorariaDTO planilla) throws ErrorServiceException {
        try {
            String uri = "http://appseguridad:9000/api/v1/planilla/crear";
            restTemplate.postForEntity(uri, planilla, PlanillaHorariaDTO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public PlanillaHorariaDTO presenteYSalida(String email, String caso) throws ErrorServiceException {
        try {
            String uri = getUri(caso);
            uri = uri + "/" + caso + "/" + email;
            ResponseEntity<PlanillaHorariaDTO> response = restTemplate.getForEntity(uri, PlanillaHorariaDTO.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                PlanillaHorariaDTO entity = response.getBody();
                return entity;
            } else {
                String errorMessage = "Error: " + response.getStatusCode() + " - " + response.getBody();
                throw new ErrorServiceException(errorMessage);
            }
        } catch (ErrorServiceException e) {
            throw e;
        } catch (HttpClientErrorException e) {
            String error = e.getMessage();
            error = error.replace("404 :", "").trim();
            error = error.replace("{\"error\":\"", "").replace("\"}", "");
            error = error.replace("\"", "");
            throw new ErrorServiceException(error);
        } catch (Exception e) {
            throw new ErrorServiceException("Error inesperado");
        }
    }
}
