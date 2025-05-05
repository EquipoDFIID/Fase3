package org.example.Modelo;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Representa un campeonato con un ID, nombre, fecha de inicio y estado.
 * El estado debe ser uno de los valores válidos: "Inscripción", "Curso" o "Finalizado".
 */
public class Campeonato {
    private int ID;
    private String Nombre;
    private LocalDate Fecha_inicio;
    private String estado;

    private static final String[] ESTADOS_VALIDOS = {"Inscripción", "Curso", "Finalizado"};

    public Campeonato() {
    }

    public Campeonato(int ID, String nombre, LocalDate fecha_inicio, String estado) {
        this.ID = ID;
        Nombre = nombre;
        Fecha_inicio = fecha_inicio;
        setEstado(estado);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public LocalDate getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        Fecha_inicio = fecha_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (Arrays.asList(ESTADOS_VALIDOS).contains(estado)) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado inválido. Debe ser 'Inscripción', 'Curso' o 'Finalizado'.");
        }
    }
}
