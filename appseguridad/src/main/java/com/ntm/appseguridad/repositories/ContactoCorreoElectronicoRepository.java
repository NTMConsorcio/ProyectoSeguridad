package com.ntm.appseguridad.repositories;

import com.ntm.appseguridad.entities.ContactoCorreoElectronico;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoCorreoElectronicoRepository extends BaseRepository<ContactoCorreoElectronico, String> {
    ContactoCorreoElectronico findByEmailAndEliminadoFalse(String email);
    boolean existsByEmailAndEliminadoFalse(String email);
}
