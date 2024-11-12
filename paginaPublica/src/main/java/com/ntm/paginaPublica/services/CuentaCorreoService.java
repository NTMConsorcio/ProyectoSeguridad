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
            if (correo.length() < 7) {
                throw new Exception("Longitud incorrecta");
            }
            if (correo.isEmpty() || correo == null) {
                throw new Exception("No puede encontrarse vacío");
            }
            if (!correo.contains("@") || !correo.contains(".")) {
                throw new Exception("El mail ingresado no es correcto.");
            }
            CuentaCorreo cuentaCorreo = new CuentaCorreo();
            cuentaCorreo.setCorreo(correo);
            cuentaCorreoRepository.save(cuentaCorreo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
