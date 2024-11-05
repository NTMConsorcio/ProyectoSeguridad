package com.ntm.appseguridad.entities;

import com.ntm.appseguridad.entities.enums.Rol;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)


public class Usuario extends Base{
    public String cuenta;
    public String clave;
    public Rol rol;
}
