package com.ntm.appseguridad.entities;

public class CacheLegajoSingleton {
    private static CacheLegajoSingleton instance;
    private int contadorLegajo;
    private CacheLegajoSingleton() {
    }

    public static CacheLegajoSingleton getInstance() {
        if (instance == null) {
            instance = new CacheLegajoSingleton();
        }
        return instance;
    }

    public int getContadorLegajo() {
        return contadorLegajo;
    }

    public void addContadorLegajo() {
        contadorLegajo = contadorLegajo + 1;
    }

    public void setContadorLegajo(int num) {
        contadorLegajo = num;
    }
}
