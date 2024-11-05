package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String>{
    Usuario findByEmailAndEliminadoFalse(String email);
    Usuario findByEmailAndClaveAndEliminadoFalse(String email, String clave);
}
