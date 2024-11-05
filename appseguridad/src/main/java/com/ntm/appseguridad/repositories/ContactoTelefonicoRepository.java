package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.ContactoTelefonico;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoTelefonicoRepository extends BaseRepository<ContactoTelefonico, String> {
    ContactoTelefonico findByTelefonoAndEliminadoFalse(String telefono);
}
