package com.ntm.clienteadministrativo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HabitanteDTO extends PersonaDTO{
    private InmuebleDTO inmueble;
}
