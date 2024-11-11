package com.ntm.paginaPublica.rest;

import com.ntm.paginaPublica.dto.ImagenDTO;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import org.springframework.stereotype.Service;

@Service
public class ImagenDAORest extends BaseDAORestImpl<ImagenDTO> {
    @Override
    public String getUri(String caso) throws ErrorDAOException {
        return "http://localhost:9000/api/v1/imagen";
    }
}
