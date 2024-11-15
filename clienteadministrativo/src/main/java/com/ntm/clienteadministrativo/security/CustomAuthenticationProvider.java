package com.ntm.clienteadministrativo.security;

import com.ntm.clienteadministrativo.dto.UsuarioDTO;
import com.ntm.clienteadministrativo.services.AuthService;
import com.ntm.clienteadministrativo.services.UsuarioDTOService;
import com.ntm.clienteadministrativo.services.error.ErrorServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;
    private final UsuarioDTOService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || password == null) {
            throw new AuthenticationServiceException("Usuario o contraseña faltante");
        }

        UsuarioDTO user = new UsuarioDTO();
        user.setClave(password);
        user.setCuenta(username);

        try {

            String token = authService.authenticateWithApi(user);
            if (token == null) {
                throw new AuthenticationServiceException("Usuario o contraseña incorrectos");
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("token", token);
            UserDetails userDetails = userService.loadUserByUsername(username);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    token,
                    userDetails.getAuthorities());
            return auth;
        } catch (ErrorServiceException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
