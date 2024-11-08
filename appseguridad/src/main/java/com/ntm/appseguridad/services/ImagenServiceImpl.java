package com.ntm.appseguridad.services;

import com.ntm.appseguridad.entities.Imagen;
import com.ntm.appseguridad.entities.Provincia;
import com.ntm.appseguridad.mappers.ImagenMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.ImagenRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImagenServiceImpl extends BaseServiceImpl<Imagen,String> implements ImagenService {
    private final ImagenRepository imagenRepository;
    private final ImagenMapper imagenMapper;

    public ImagenServiceImpl(BaseRepository<Imagen, String> baseRepository, ImagenRepository imagenRepository, ImagenMapper imagenMapper) {
        super(baseRepository);
        this.imagenRepository = imagenRepository;
        this.imagenMapper = imagenMapper;
    }

    @Override
    public <D> List<D> convertToDtoList(List<Imagen> entities) {
        return (List<D>) imagenMapper.toDtoList(entities);
    }

    /*
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

        */
    public boolean validar(Imagen archivo, String caso) throws ErrorServiceException{

        try {
            if (archivo.getContenido() == null) {
                throw new ErrorServiceException("La imagen no posee contenido");
            }
            if (archivo.getNombre() == null || archivo.getNombre().isEmpty()) {
                throw new ErrorServiceException("La imagen no posee nombre");
            }
            if (archivo.getMime() == null || archivo.getMime().isEmpty()) {
                throw new ErrorServiceException("La imagen no posee mime");
            }

        } catch (ErrorServiceException e) {
            throw e;
        } catch (Exception ex){
            throw new ErrorServiceException("Error de Sistemas");
        }
        return true;
    }
}
