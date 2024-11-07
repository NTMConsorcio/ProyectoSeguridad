package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProvinciaDTO extends BaseDTO {
    private String nombre;
    private PaisDTO pais;
}
