package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String>{
    public Usuario findByEmailAndEliminadoFalse(String email);
    public Usuario findByEmailAndClaveAndEliminadoFalse(String email, String clave);
    
}
