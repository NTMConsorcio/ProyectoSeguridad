package com.ntm.appseguridad.repositories;


import com.ntm.appseguridad.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String>{
    Usuario findByCuentaAndEliminadoFalse(String cuenta);
    Usuario findByCuentaAndClaveAndEliminadoFalse(String cuenta, String clave);
    @Query("SELECT u FROM Usuario u WHERE u.id = (SELECT p.usuario.id FROM Persona p WHERE p.id = :personaId)")
    Usuario findByPersonaId(@Param("personaId") String personaId);
}
