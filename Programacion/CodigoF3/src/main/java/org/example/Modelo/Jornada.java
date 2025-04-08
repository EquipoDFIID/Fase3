package org.example.Modelo;

import java.util.ArrayList;

public class Jornada {
    private String idJornada;
    private String fecha;
    private Campeonato campeonato;
    private ArrayList <Enfrentamiento> listaEnfrentamientos;

    public Jornada() {
    }

    public Jornada(String idJornada, String fecha, Campeonato campeonato, ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.idJornada = idJornada;
        this.fecha = fecha;
        this.campeonato = campeonato;
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    public String getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(String idJornada) {
        this.idJornada = idJornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public ArrayList<Enfrentamiento> getListaEnfrentamientos() {
        return listaEnfrentamientos;
    }

    public void setListaEnfrentamientos(ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.listaEnfrentamientos = listaEnfrentamientos;
    }
}