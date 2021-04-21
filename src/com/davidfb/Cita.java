package com.davidfb;

public class Cita {
    Doctor doctor;
    Paciente paciente;

    public Cita(Doctor doctor, Paciente paciente) {
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
