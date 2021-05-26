package com.davidfb;

import java.util.Scanner;
import java.util.UUID;

public class Paciente implements DeAlta {

    private String id;
    private String nombre;

    public Paciente() {
    }

    private Paciente(String name) {
        this.id = UUID.randomUUID().toString();
        this.nombre = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PACIENTE " + nombre;
    }

    @Override
    public DeAlta darDeAlta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de el/la paciente: ");
        String nombre = scanner.nextLine();
        Paciente nuevoPaciente = new Paciente(nombre);
        System.out.println(nuevoPaciente + "ha sido dado de alta!");
        return nuevoPaciente;
    }
}
