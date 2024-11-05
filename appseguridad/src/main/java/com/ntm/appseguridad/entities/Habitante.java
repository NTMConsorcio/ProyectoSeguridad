package com.ntm.appseguridad.entities;

import jakarta.persistence.OneToOne;

public class Habitante extends Persona {
    @OneToOne
    private Inmueble inmueble;
}
