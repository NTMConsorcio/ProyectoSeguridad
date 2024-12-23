package com.ntm.appseguridad.seguridad;

import com.ntm.appseguridad.entities.Usuario;
import com.ntm.appseguridad.services.UsuarioService;
import com.ntm.appseguridad.services.UsuarioServiceImpl;
import com.ntm.appseguridad.services.error.ErrorServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var user = userService.buscarPorEmail(username).orElseThrow();
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());
            permisos.add(p);
            return UserPrincipal.builder()
                    .userId(user.getId())
                    .email(user.getCuenta())
                    .authorities(permisos)
                    .password(user.getClave())
                    .build();

        } catch (ErrorServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
