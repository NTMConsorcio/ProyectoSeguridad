package com.ntm.appseguridad.controllers;

import com.ntm.appseguridad.entities.Imagen;
import com.ntm.appseguridad.services.ImagenServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/imagen")
public class ImagenController extends BaseControllerImpl<Imagen, ImagenServiceImpl>{
    public ImagenController(ImagenServiceImpl service) {super(service);}

}
