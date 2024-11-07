package com.ntm.appseguridad.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HabitanteDTO extends PersonaDTO{
    private String idInmueble;
}
