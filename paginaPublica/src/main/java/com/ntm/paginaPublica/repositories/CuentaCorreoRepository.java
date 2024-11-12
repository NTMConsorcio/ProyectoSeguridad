package com.ntm.paginaPublica.repositories;

import com.ntm.paginaPublica.entities.CuentaCorreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CuentaCorreoRepository extends JpaRepository<CuentaCorreo, String> {
}
