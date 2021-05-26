package com.davidfb;

import java.util.Scanner;
import java.util.UUID;

public class Doctor implements DeAlta {

    private String id;
    private String nombre;
    private String especialidad;
    private boolean administrador;
    private String clave;

    public Doctor() {
    }

    private Doctor(String name, Especialidad especialidad) {
        this.id = UUID.randomUUID().toString();
        this.nombre = name;
        this.especialidad = especialidad.toString();
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

    public void setName(String name) {
        this.nombre = name;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    @Override
    public String toString() {
        return especialidad + " " + nombre;
    }

    @Override
    public DeAlta darDeAlta() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        int opcion;

        System.out.print("Introduce el nombre del doctor/doctora: ");
        nombre = scanner.nextLine();
        Especialidad especialidad;
        System.out.println("Elije la Especialidad");
        for (int i = 0; i < Especialidad.values().length; i++) {
            System.out.println((i + 1) + " - " + Especialidad.values()[i]);
        }
        try {
            System.out.print("Introduce una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            especialidad = Especialidad.values()[opcion];
        } catch (Exception e) {
            System.out.println("Ocurrio un error, vuelve a intentar");
            return darDeAlta();
        }
        Doctor nuevoDoctor = new Doctor(nombre, especialidad);
        boolean repetir;
        System.out.print("""
                Este doctor tiene derechos de administrador?
                1 - si
                2 - no
                Introduce opcion:\s""");
        do {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                repetir = false;
            } catch (Exception e) {
                System.out.println("Opcion Invalida!");
                repetir = true;
            }
        } while (repetir);

        if (opcion == 1) {
            nuevoDoctor.administrador = true;
            System.out.print("Crea una clave: ");
            nuevoDoctor.clave = scanner.nextLine();
        } else {
            nuevoDoctor.administrador = false;
        }

        System.out.println(nuevoDoctor + " ha sido dado de alta!");
        return nuevoDoctor;
    }

    public String getClave() {
        return clave;
    }
}
