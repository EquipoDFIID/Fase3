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
    private Campeonato campeonato;
    private ArrayList <Enfrentamiento> listaEnfrentamientos;

    public Jornada() {
    }

    public Jornada(ArrayList<Enfrentamiento> enfrentamientos, LocalDate now, Campeonato campeonato) {
        this.listaEnfrentamientos = enfrentamientos;
        this.fecha = now;
        this.campeonato = campeonato;
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