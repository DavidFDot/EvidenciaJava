package com.davidfb;

import java.util.UUID;

public class Paciente implements DeAlta {

    private UUID id;
    private String nombre;

    public Paciente(String name, Especialidad especialidad) {
        this.id = UUID.randomUUID();
        this.nombre = name;
        darDeAlta();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "El/La paciente " + nombre;
    }

    @Override
    public void darDeAlta() {
        System.out.println(this + "ha sido dado de alta!");
    }
}
