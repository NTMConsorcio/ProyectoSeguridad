package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String>{
    Usuario findByCuentaAndEliminadoFalse(String cuenta);
    Usuario findByCuentaAndClaveAndEliminadoFalse(String cuenta, String clave);
}
