package org.example.Modelo;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Representa un campeonato con un ID, nombre, fecha de inicio y estado.
 * El estado debe ser uno de los valores válidos: "Inscripción", "Curso" o "Finalizado".
 */
public class Campeonato {
    private String ID;
    private String Nombre;
    private LocalDate fechaInicio;
    private String estado;

    private static final String[] ESTADOSVALIDOS = {"Inscripción", "Curso", "Finalizado"};

    public Campeonato() {
    }

    public Campeonato(String ID, String nombre, LocalDate fechaInicio, String estado) {
        this.ID = ID;
        this.Nombre = nombre;
        this.fechaInicio = fechaInicio;
        setEstado(estado);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public LocalDate getFecha_inicio() {
        return fechaInicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (Arrays.asList(ESTADOSVALIDOS).contains(estado)) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'Inscripción', 'Curso' o 'Finalizado'.");
        }
    }
}
