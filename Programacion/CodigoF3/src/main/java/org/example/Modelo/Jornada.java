package org.example.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Representa una jornada dentro de un campeonato, la cual contiene
 * una lista de enfrentamientos que se disputan en una fecha específica.
 */
public class Jornada {
    private int idJornada;
    private LocalDate fecha;
    private int campeonato;
    private ArrayList <Enfrentamiento> listaEnfrentamientos;

    public Jornada() {
    }

    public Jornada(int idJornada, LocalDate fecha, int campeonato, ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.idJornada = idJornada;
        this.fecha = fecha;
        this.campeonato = campeonato;
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    public Jornada(LocalDate fecha, int campeonato, ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.fecha = fecha;
        this.campeonato = campeonato;
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(int campeonato) {
        this.campeonato = campeonato;
    }

    public ArrayList<Enfrentamiento> getListaEnfrentamientos() {
        return listaEnfrentamientos;
    }

    public void setListaEnfrentamientos(ArrayList<Enfrentamiento> listaEnfrentamientos) {
        this.listaEnfrentamientos = listaEnfrentamientos;
    }
}