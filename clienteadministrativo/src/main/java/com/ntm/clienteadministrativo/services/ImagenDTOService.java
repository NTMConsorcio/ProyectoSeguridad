package com.ntm.clienteadministrativo.services;

import com.ntm.clienteadministrativo.dto.ImagenDTO;
import com.ntm.clienteadministrativo.rest.ImagenDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImagenDTOService {
    @Autowired
    ImagenDAORest dao;

    public void crear(String nombre, MultipartFile archivo) throws ErrorServiceException {

        try {
            ImagenDTO imagen = new ImagenDTO();
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(nombre);
            imagen.setContenido(archivo.getBytes());
            imagen.setEliminado(false);

            dao.crear(ImagenDTO.class, imagen);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public void modificar(String id, MultipartFile archivo) throws ErrorServiceException {

        try {

            ImagenDTO imagen = new ImagenDTO();
            imagen.setId(id);
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            dao.actualizar(imagen);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    public ImagenDTO buscar (String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            ImagenDTO obj = dao.buscar(ImagenDTO.class, id);

            return obj;

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public void eliminar(String id) throws ErrorServiceException {

        try {

            if (id == null) {
                throw new ErrorServiceException("Debe indicar el id");
            }

            dao.eliminar(id);

        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }

    }

    public List<ImagenDTO> listar() throws ErrorServiceException {
        try {
            return dao.listar(ImagenDTO[].class);
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ErrorServiceException("Error de sistema");
        }
    }

    public Map<String, String> obtenerImagenesEnBase64(List<ImagenDTO> imagenes) {
        Map<String, String> imagenesMap = new HashMap<>();

        for (ImagenDTO imagen : imagenes) {
            String base64 = Base64.getEncoder().encodeToString(imagen.getContenido());
            String key = imagen.getId();
            imagenesMap.put(key, base64);
        }

        return imagenesMap;
    }
}
