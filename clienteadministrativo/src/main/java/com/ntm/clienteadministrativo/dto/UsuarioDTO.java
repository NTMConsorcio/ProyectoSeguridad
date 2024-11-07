package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class UsuarioDTO extends BaseDTO{
    public String cuenta;
    public String clave;
    public String rol;
}
