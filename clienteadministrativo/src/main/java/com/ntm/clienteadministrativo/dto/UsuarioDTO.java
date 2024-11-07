package com.ntm.clienteadministrativo.dto;

import com.ntm.clienteadministrativo.dto.enums.Rol;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UsuarioDTO extends BaseDTO{
    public String cuenta;
    public String clave;
    public Rol rol;
}
