package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.entities.CuentaCorreo;
import com.ntm.paginaPublica.repositories.CuentaCorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaCorreoService {
    @Autowired
    private CuentaCorreoRepository cuentaCorreoRepository;

    public void crear(String correo) {
        try {
            CuentaCorreo cuentaCorreo = new CuentaCorreo();
            cuentaCorreo.setCorreo(correo);
            cuentaCorreoRepository.save(cuentaCorreo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
