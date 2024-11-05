package com.ntm.appseguridad.services;



import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.repositories.BaseRepository;
import com.ntm.appseguridad.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, String> implements UsuarioService{
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(BaseRepository<Usuario, String> baserepository, UsuarioRepository usuarioRepository) {super(baserepository);
        this.repository = usuarioRepository;
    }
    @Override
    public Usuario searchByEmail(String email) throws Exception {
        try {
            Usuario usuario = repository.findByEmailAndEliminadoFalse(email);
            return usuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Usuario searchByEmailAndClave(String email, String clave) throws Exception {
        try {
            Usuario usuario = repository.findByEmailAndClaveAndEliminadoFalse(email, clave);
            return usuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
