package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.ServicioDTO;
import com.ntm.paginaPublica.dto.UnidadDeNegocioDTO;
import com.ntm.paginaPublica.rest.ServicioDAORest;
import com.ntm.paginaPublica.rest.UnidadDeNegocioDAORest;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioDTOService {
    @Autowired
    private ServicioDAORest dao;

    public List<ServicioDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ServicioDTO[].class);
        } catch (ErrorDAOException ex) {
            throw new ErrorServiceException("Error al listar servicios");
        }
    }

    public HashMap<String, Integer> getMapIndices(List<ServicioDTO> lista) {

        HashMap<String, Integer> indiceMap = new HashMap<>();

        for (int i = 0; i < lista.size(); i++) {
            indiceMap.put(lista.get(i).getNombre(), i);
        }
        return indiceMap;
    }

    public HashMap<String, String> obtenerImagenEnBase64(List<ServicioDTO> lista) {
        HashMap<String, String> imagenesMap = new HashMap<>();

        for (ServicioDTO elem : lista) {
            String base64 = Base64.getEncoder().encodeToString(elem.getImagen().getContenido());
            String key = elem.getNombre();
            imagenesMap.put(key, base64);
        }

        return imagenesMap;
    }
}
