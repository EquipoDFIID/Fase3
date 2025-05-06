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
    private String estado;

    private static final String[] ESTADOSVALIDOS = {"Inscripción", "Curso", "Finalizado"};

    public Campeonato() {
    }


    public Campeonato(int ID, String nombre , String estado) {
        this.ID = ID;
        this.Nombre = nombre;
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
