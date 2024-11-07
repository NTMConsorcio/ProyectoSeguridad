package com.ntm.appseguridad.services;



import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UsuarioRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, String> implements UsuarioService{
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(BaseRepository<Usuario, String> baserepository, UsuarioRepository usuarioRepository) {super(baserepository);
        this.repository = usuarioRepository;
    }
    @Override
    public Usuario searchByCuenta(String cuenta) throws Exception {
        try {
            Usuario usuario = repository.findByCuentaAndEliminadoFalse(cuenta);
            return usuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Usuario searchByCuentaAndClave(String cuenta, String clave) throws Exception {
        try {
            Usuario usuario = repository.findByCuentaAndClaveAndEliminadoFalse(cuenta, clave);
            return usuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean validar(Usuario entity, String caso) throws Exception {
        try {
            if (entity.getCuenta() == null || entity.getCuenta().isEmpty()) {
                throw new ErrorServiceException("Debe indicar el mail");
            }

            if (entity.getClave() == null || entity.getClave().isEmpty()) {
                throw new ErrorServiceException("Debe indicar la clave");
            }

            if (entity.getRol() == null){
                throw new ErrorServiceException("Debe indicar el rol");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El usuario ya existe en el sistema");
                }
                if (repository.findByCuentaAndClaveAndEliminadoFalse(entity.getCuenta(),entity.getClave()) != null) {
                    throw new ErrorServiceException("El usuario ya existe en el sistema");
                }
            } else {
                Optional<Usuario> uu = repository.findByIdAndEliminadoFalse(entity.getId());
                if (uu.isPresent()) {
                    Usuario e = uu.get();
                    if (!e.getId().equals(entity.getId())) {
                        throw new ErrorServiceException("El empleado especificado no existe en el sistema");
                    }
                }
            }
            return true;
        } catch (ErrorServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ErrorServiceException("Error de sistemas");
        }
    }
}
