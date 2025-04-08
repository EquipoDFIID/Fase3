package org.example.Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Enfrentamiento {
    private String idEnfrentamiento;
    private LocalDate fechEnfrentamiento;
    private LocalTime horaEnfrentamiento;
    private Equipo equipoAtacante;
    private Equipo equipoDefensor;
    private Jornada jornada;
    private Equipo EquipoGanador;

    public Enfrentamiento() {

    }

    public Enfrentamiento(String idEnfrentamiento, LocalDate fechEnfrentamiento, LocalTime horaEnfrentamiento, Equipo equipoAtacante, Equipo equipoDefensor, Jornada jornada, Equipo equipoGanador) {
        this.idEnfrentamiento = idEnfrentamiento;
        this.fechEnfrentamiento = fechEnfrentamiento;
        this.horaEnfrentamiento = horaEnfrentamiento;
        this.equipoAtacante = equipoAtacante;
        this.equipoDefensor = equipoDefensor;
        this.jornada = jornada;
        EquipoGanador = equipoGanador;
    }

    public String getIdEnfrentamiento() {
        return idEnfrentamiento;
    }

    public void setIdEnfrentamiento(String idEnfrentamiento) {
        this.idEnfrentamiento = idEnfrentamiento;
    }

    public LocalDate getFechEnfrentamiento() {
        return fechEnfrentamiento;
    }

    public void setFechEnfrentamiento(LocalDate fechEnfrentamiento) {
        this.fechEnfrentamiento = fechEnfrentamiento;
    }

    public LocalTime getHoraEnfrentamiento() {
        return horaEnfrentamiento;
    }

    public void setHoraEnfrentamiento(LocalTime horaEnfrentamiento) {
        this.horaEnfrentamiento = horaEnfrentamiento;
    }

    public Equipo getEquipoAtacante() {
        return equipoAtacante;
    }

    public void setEquipoAtacante(Equipo equipoAtacante) {
        this.equipoAtacante = equipoAtacante;
    }

    public Equipo getEquipoDefensor() {
        return equipoDefensor;
    }

    public void setEquipoDefensor(Equipo equipoDefensor) {
        this.equipoDefensor = equipoDefensor;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Equipo getEquipoGanador() {
        return EquipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        EquipoGanador = equipoGanador;
    }

}