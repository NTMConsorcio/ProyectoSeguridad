package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Imagen;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.web.multipart.MultipartFile;


public interface ImagenService extends BaseService<Imagen, String> {
    Imagen save(MultipartFile archivo) throws ErrorServiceException;
    Imagen update(String id, MultipartFile archivo) throws ErrorServiceException;
}
