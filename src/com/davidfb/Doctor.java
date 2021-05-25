package com.davidfb;

import java.util.UUID;

public class Doctor implements DeAlta {

    private UUID id;
    private String nombre;
    private Especialidad especialidad;
    private boolean administrador;

    public Doctor(String name, Especialidad especialidad) {
        this.id = UUID.randomUUID();
        this.nombre = name;
        this.especialidad = especialidad;
        darDeAlta();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public String getEspecialidad() {
        return especialidad.toString();
    }

    public boolean isAdministrador() {
        return administrador;
    }

    @Override
    public String toString() {
        return "El/La " + especialidad + " " + nombre;
    }

    @Override
    public void darDeAlta() {
        System.out.println(this + "ha sido dado de alta!");
    }
}
