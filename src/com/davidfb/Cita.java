package com.davidfb;

public class Cita {
    private Doctor doctor;
    private Paciente paciente;
    private String fecha;
    private String motivo;


    public Cita() {

    }
    public Cita(Doctor doctor, Paciente paciente, String fecha, String motivo) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.fecha = fecha;
        this.motivo = motivo;
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

    public String getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }
}
