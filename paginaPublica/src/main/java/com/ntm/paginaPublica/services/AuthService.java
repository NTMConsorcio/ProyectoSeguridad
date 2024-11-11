package com.ntm.paginaPublica.services;

import com.ntm.paginaPublica.dto.UsuarioDTO;
import com.ntm.paginaPublica.rest.AuthDAORest;
import com.ntm.paginaPublica.rest.error.ErrorDAOException;
import com.ntm.paginaPublica.services.error.ErrorServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthService {
    @Autowired
    private AuthDAORest authDAO;

    @Autowired
    private UsuarioDTOService userService;

    public void authenticateWithApi() throws ErrorServiceException {
        try {
            UsuarioDTO usuario = userService.crear("empresa@gmail.com", "87654321");
            String token = authDAO.authenticate(usuario);
            JSONObject jsonObject = new JSONObject(token);

            token = jsonObject.getString("accessToken");

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
        } catch (ErrorDAOException e) {
            throw new ErrorServiceException("Error al autenticar el usuario");
        }
    }

}
