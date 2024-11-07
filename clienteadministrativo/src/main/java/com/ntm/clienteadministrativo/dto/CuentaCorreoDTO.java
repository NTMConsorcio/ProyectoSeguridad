package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CuentaCorreoDTO extends BaseDTO {
    private String correo;
    private String clave;
    private String puerto;
    private String smtp;
    private boolean tls;
    private EmpresaDTO empresa;
}
