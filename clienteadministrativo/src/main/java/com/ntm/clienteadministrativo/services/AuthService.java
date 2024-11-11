package com.ntm.clienteadministrativo.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.rest.AuthDAORest;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthDAORest authDAO;


    public String authenticateWithApi(UsuarioDTO usuario) throws ErrorServiceException {
        try {
            String token = authDAO.authenticate(usuario);
            JSONObject jsonObject = new JSONObject(token);

            token = jsonObject.getString("accessToken");
            return token;
        } catch (ErrorServiceException e) {
            throw new ErrorServiceException("Error con la autenticación");
        }
    }
}
