package org.example.Controladores;


import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Enfrentamiento;
import org.example.Modelo.EnfrentamientoDAO;
import org.example.Modelo.Equipo;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Clase `EnfrentamientoController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los enfrentamientos, interactuando con la capa de datos (`EnfrentamientoDAO`).
 */
public class EnfrentamientoController {
    private EnfrentamientoDAO dao;
    private ArrayList<Enfrentamiento> listaEnfrentamientos = new ArrayList<>();

    public EnfrentamientoController(EnfrentamientoDAO enfrentamientoDAO) {
        this.dao = enfrentamientoDAO;
    }

    /**
     * Crea un nuevo enfrentamiento y lo guarda en la base de datos.
     *
     * @param enfrentamientos Objeto `Enfrentamiento` que se desea crear.
     */
    public void crearEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) throws Exception {
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            dao.altaEnfrentamiento(enfrentamiento);
        }
    }

    public ArrayList<Enfrentamiento> rellenarEquiposEnfrentamientos() throws Exception {
        return listaEnfrentamientos;
    }

    public boolean asignarGanadorEnfrentamiento(Enfrentamiento enfrentamiento) throws Exception {
        return dao.asignarGanadorEnfrentamiento(enfrentamiento);
    }

    public ArrayList<Enfrentamiento> selectEnfrentamientosJornada(int idJornada) throws Exception {
        return dao.selectEnfrentamientosJornada(idJornada);
    }

    public String mostrarProcedimientoResultado() throws Exception {
        return dao.procedimientoEnfrentamientosUltimaJornada();
    }
}