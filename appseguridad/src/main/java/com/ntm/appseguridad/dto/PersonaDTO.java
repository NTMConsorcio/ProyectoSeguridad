package com.ntm.appseguridad.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class PersonaDTO extends BaseDTO{
    protected int documento;
    protected String nombre;
    protected String apellido;
    protected List<ContactoDTO> Contactos;
    protected UsuarioDTO Usuario;
}
