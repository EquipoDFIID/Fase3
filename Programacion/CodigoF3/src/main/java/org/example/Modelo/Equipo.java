package org.example.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Equipo {
    private String idEquipo;
    private String nombre;
    private LocalDate fechaFund;
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Enfrentamiento> listaEnfrentamientos;

    public Equipo() {
    }


    public Equipo(String idEquipo, LocalDate fechaFund, String nombre) {
        this.idEquipo = idEquipo;
        this.fechaFund = fechaFund;
        this.nombre = nombre;
    }

    public Equipo(String idEquipo, String nombre, LocalDate fechaFund, ArrayList<Jugador> listaJugadores, ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFund = fechaFund;
        this.listaJugadores = listaJugadores;
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaFund() {
        return fechaFund;
    }

    public void setFechaFund(LocalDate fechaFund) {
        this.fechaFund = fechaFund;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public ArrayList<Enfrentamiento> getListaEnfrentamientos() {
        return listaEnfrentamientos;
    }

    public void setListaEnfrentamientos(ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.listaEnfrentamientos = listaEnfrentamientos;
    }
}