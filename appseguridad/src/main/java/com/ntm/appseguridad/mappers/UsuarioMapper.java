package com.ntm.appseguridad.mappers;

import com.ntm.appseguridad.dto.UsuarioDTO;
import com.ntm.appseguridad.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toUsuarioDTO(Usuario usuario);
    Usuario toUsuario(UsuarioDTO usuarioDTO);
}
