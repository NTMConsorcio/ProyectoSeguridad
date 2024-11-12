package com.ntm.appseguridad.services;


import com.ntm.appseguridad.dto.UsuarioDTO;
import com.ntm.appseguridad.entities.Servicio;
import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.entities.enums.Rol;
import com.ntm.appseguridad.mappers.UsuarioMapper;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UsuarioRepository;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, String> implements UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(BaseRepository<Usuario, String> baserepository, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        super(baserepository);
        this.repository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDTO Crear(UsuarioDTO usuarioDto) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioDto);
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
        Usuario usuarioGuardado = repository.save(usuario);
        return usuarioMapper.toUsuarioDTO(usuarioGuardado);
    }

    public UsuarioDTO crearUsuarioDTO(String cuenta, String clave, Rol rol) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setClave(clave);
        usuario.setRol(rol);
        usuario.setCuenta(cuenta);
        return usuario;
    }
    public UsuarioDTO searchByCuentaDto(String cuenta) throws Exception {
        try {
            Usuario usuario = searchByCuenta(cuenta);
            return usuarioMapper.toUsuarioDTO(usuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario searchByCuenta(String cuenta) throws Exception {
        try {
            Usuario usuario = repository.findByCuentaAndEliminadoFalse(cuenta);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario searchByCuentaAndClave(String cuenta, String clave) throws Exception {
        try {
            Usuario usuario = repository.findByCuentaAndClaveAndEliminadoFalse(cuenta, clave);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public <D> D convertToDto(Usuario entity) {
        return (D) usuarioMapper.toUsuarioDTO(entity);
    }

    @Override
    public <D> List<D> convertToDtoList(List<Usuario> entities) {
        return (List<D>) usuarioMapper.toDtoList(entities);
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

            if (entity.getRol() == null) {
                throw new ErrorServiceException("Debe indicar el rol");
            }

            if (caso.equals("SAVE")) {
                if (repository.findByIdAndEliminadoFalse(entity.getId()).isPresent()) {
                    throw new ErrorServiceException("El usuario ya existe en el sistema");
                }
                if (repository.findByCuentaAndClaveAndEliminadoFalse(entity.getCuenta(), entity.getClave()) != null) {
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

    public Optional<Usuario> buscarPorEmail(String email) throws ErrorServiceException {
        try {
            Usuario usuario = repository.findByCuentaAndEliminadoFalse(email);
            if (usuario == null) {
                return Optional.empty();
            }
            return Optional.of(usuario);
        } catch (Exception ex) {
            throw new ErrorServiceException("Error encontrando el usuario");
        }
    }
}
