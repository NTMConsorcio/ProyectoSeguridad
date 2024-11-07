package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Imagen;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ImagenRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServiceImpl extends BaseServiceImpl<Imagen,String> implements ImagenService {
    private final ImagenRepository imagenRepository;

    public ImagenServiceImpl(BaseRepository<Imagen, String> baseRepository, ImagenRepository imagenRepository) {
        super(baseRepository);
        this.imagenRepository = imagenRepository;
    }

    @Override
    @Transactional
    public Imagen save(MultipartFile archivo) throws ErrorServiceException {
        try {

            validar(archivo);

            Imagen imagen = new Imagen();
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            imagen.setEliminado(false);

            return repository.save(imagen);


        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            throw new ErrorServiceException("Error de Sistemas");
        }
    }

    @Override
    @Transactional
    public Imagen update(String idImagen, MultipartFile archivo) throws ErrorServiceException{

        try {

            validar(archivo);

            Imagen imagen = findById(idImagen);
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());

            return repository.save(imagen);

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            throw new ErrorServiceException("Error de Sistemas");
        }

    }

    @Override
    public boolean validar(Imagen imagen, String caso) {
        return true;
    }

    public void validar(MultipartFile archivo) throws ErrorServiceException{

        try {

            if (archivo == null || archivo.isEmpty()){
                throw new ErrorServiceException("Debe indicar el nombre");
            }

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            throw new ErrorServiceException("Error de Sistemas");
        }
    }
}
