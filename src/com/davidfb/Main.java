package com.davidfb;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int opcion = 0;
    static final String[] saveFiles = {"src/db/citas", "src/db/doctores", "src/db/pacientes"};
    static List<Doctor> doctores = new ArrayList<>();
    static List<Paciente> pacientes = new ArrayList<>();
    static List<Cita> citas = new ArrayList<>();

    public static void main(String[] args) {

        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Identifiquese");
        if (identifiquese()) {
            menu();
        } else {
            System.out.println("Identificacion fallida");
        }
        save();
    }

    private static void menu() {
        boolean running = true;
        do {
            System.out.print("""
                    Sistema de citas medicas
                    Elije una opcion:
                    1 - Dar de alta a Doctor
                    2 - Dar de alta a paciente
                    3 - Hacer cita
                    4 - Cerrar
                    Opcion seleccionada:\s""");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> darDeAltaDoctor();
                    case 2 -> darDeAltaPaciente();
                    case 3 -> crearCita();
                    case 4 -> running = false;
                    default -> System.out.println("Esa opcion no existe");
                }
            } catch (Exception e) {
                System.out.println("Opcion no valida");
            }
        } while (running);
    }

    private static boolean identifiquese() {
        if (pacientes.size() < 1) {
            System.out.println("No hay parfiles, crea un Perfil");
            darDeAltaDoctor();
        }
        System.out.println("Quien eres?");
        listarDoctores();
        System.out.print("Selecciona quien eres: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (doctores.get(opcion - 1).isAdministrador()) {
            System.out.print("Introduce la clave: ");
            String clave = scanner.nextLine();
            if (doctores.get(opcion - 1).getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }

    private static void darDeAltaDoctor() {
        Doctor doctor = new Doctor();
        doctor = (Doctor) doctor.darDeAlta();
        doctores.add(doctor);
    }

    private static void darDeAltaPaciente() {
        Paciente paciente = new Paciente();
        paciente = (Paciente) paciente.darDeAlta();
        pacientes.add(paciente);
    }

    private static void crearCita() {
        Doctor doctor;
        Paciente paciente;
        String date;
        String motivo;
        int opcion;
        try {
            System.out.println("Asigna el Doctor para la cita");
            listarDoctores();
            System.out.print("Eleccion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            doctor = doctores.get(opcion - 1);
            System.out.println("Asigna el Paciente para la cita");
            listarPacientes();
            System.out.print("Eleccion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            paciente = pacientes.get(opcion - 1);
            System.out.print("Escribe la fecha: ");
            date = scanner.nextLine();
            System.out.print("Escribe el motivo: ");
            motivo = scanner.nextLine();

            Cita cita = new Cita(doctor, paciente, date, motivo);
            citas.add(cita);
        } catch (Exception e) {
            System.out.println("Algo salio mal");
            crearCita();
        }
    }

    public static void listarDoctores() {
        doctores.sort(new Comparator<>() {
            @Override
            public int compare(Doctor doc1, Doctor doc2) {
                return doc1.getNombre().compareTo(doc2.getNombre());
            }
        });
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println((i + 1) + " - " + doctores.get(i));
        }
    }

    public static void listarPacientes() {
        pacientes.sort(new Comparator<>() {
            @Override
            public int compare(Paciente paciente1, Paciente paciente2) {
                return paciente1.getNombre().compareTo(paciente2.getNombre());
            }
        });
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i));
        }
    }

    public static void save() {
        try {
            for (int i = 0; i < saveFiles.length; i++) {
                PrintWriter writer = new PrintWriter(saveFiles[i]);
                switch (i) {
                    case 0:
                        for (Cita cita : citas) {
                            writer.println(cita.getDoctor() + "," +
                                    cita.getPaciente() + "," +
                                    cita.getFecha() + "," +
                                    cita.getMotivo());
                        }
                        writer.close();
                        break;
                    case 1:
                        for (Doctor doctor : doctores) {
                            writer.println(doctor.getId() + "," +
                                    doctor.getNombre() + "," +
                                    doctor.getEspecialidad());
                        }
                        writer.close();
                        break;
                    case 2:
                        for (Paciente paciente : pacientes) {
                            writer.println(paciente.getId() + "," +
                                    paciente.getNombre());
                        }
                        writer.close();
                        break;
                    default:
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        System.out.println("Guardado Exitoso!");
    }

    public static void load() throws IOException {

        File temp = new File(saveFiles[0]);
        if (Files.notExists(temp.toPath())) {
            for (String filePath : saveFiles) {
                temp = new File(filePath);
                temp.createNewFile();
            }
            return;
        }

        try {
            BufferedReader bufferedReader = null;
            for (int i = 0; i < saveFiles.length; i++) {
                switch (i) {
                    case 0:
                        bufferedReader = new BufferedReader(new FileReader(saveFiles[i]));
                        String[] informacion = bufferedReader.readLine().split(",");
                        do {
                            String[] doctorInfo = informacion[0].split(" ");
                            Doctor doctor = new Doctor();
                            doctor.setEspecialidad(doctorInfo[0]);
                            doctor.setName(doctorInfo[1]);
                            String[] pacienteInfo = informacion[1].split(" ");
                            Paciente paciente = new Paciente();
                            paciente.setNombre(pacienteInfo[1]);
                            Cita cita = new Cita(doctor, paciente, informacion[2], informacion[3]);
                            citas.add(cita);
                            try {
                                informacion = bufferedReader.readLine().split(",");
                            } catch (Exception e) {
                                break;
                            }
                        } while (true);
                        bufferedReader.close();
                        break;
                    case 1:
                        bufferedReader = new BufferedReader(new FileReader(saveFiles[i]));
                        String[] informacion2 = bufferedReader.readLine().split(",");
                        do {
                            Doctor doctor = new Doctor();
                            doctor.setId(informacion2[0]);
                            doctor.setName(informacion2[1]);
                            doctor.setEspecialidad(informacion2[2]);
                            doctores.add(doctor);
                            try {
                                informacion = bufferedReader.readLine().split(",");
                            } catch (Exception e) {
                                break;
                            }
                        } while (true);
                        bufferedReader.close();
                        break;
                    case 2:
                        bufferedReader = new BufferedReader(new FileReader(saveFiles[i]));
                        String[] informacion3 = bufferedReader.readLine().split(",");
                        do {
                            Paciente paciente = new Paciente();
                            paciente.setId(informacion3[0]);
                            paciente.setNombre(informacion3[1]);
                            pacientes.add(paciente);
                            try {
                                informacion = bufferedReader.readLine().split(",");
                            } catch (Exception e) {
                                break;
                            }
                        } while (true);
                        bufferedReader.close();
                        break;
                    default:
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
