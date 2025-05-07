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


    /*public List<Enfrentamiento> generarEnfrentamientos(List<Equipo> equipos) {
        List<Enfrentamiento> enfrentamientos = new ArrayList<>();
        List<Equipo> disponibles = new ArrayList<>(equipos);
        Random rand = new Random();

        while (disponibles.size() >= 2) {
            Equipo atacante = disponibles.remove(rand.nextInt(disponibles.size()));
            Equipo defensor;
            do {
                defensor = equipos.get(rand.nextInt(equipos.size()));
            } while (defensor == atacante);

            enfrentamientos.add(new Enfrentamiento(atacante, defensor));
        }

        return enfrentamientos;
    }*/


  /*public void generarEnfrentamientos() {
        StringBuilder sb = new StringBuilder();
        boolean correcto=true;
        List<Equipo> equiposDisponibles = new ArrayList<>(EquipoDAO.listaEquipos);
        Collections.shuffle(equiposDisponibles);
        ArrayList<ArrayList<Equipo>> equiposGanadoresJornada = new ArrayList<>();
        do {

            for (int i = 1; i <= equiposDisponibles.size() - 1; i++) {
                ArrayList<Equipo> ganadoresJornada = new ArrayList<>();
                sb.append("Jornada ").append(i).append(" - Ganadores:\n");

                for (int j = 1; j <= equiposDisponibles.size() / 2; j++) {
                    if (equiposDisponibles.size() < 2) correcto = false;

                    Equipo atacante = equiposDisponibles.remove(0);
                    Equipo defensor = equiposDisponibles.remove(0);
                    Enfrentamiento enfrentamiento = new Enfrentamiento();
                    enfrentamiento.setEquipoAtacante(atacante);
                    enfrentamiento.setEquipoDefensor(defensor);
                    solicitarValidarDatos();
                    enfrentamiento.setJornada(JornadaController.listaJornadas.get(i));
                    Equipo equipoGanador =preguntarResultados();
                    EnfrentamientoDAO.agregarEnfrentamientos(enfrentamiento);
                    enfrentamientos.add(enfrentamiento);
                    JOptionPane.showMessageDialog(null, "Este va a ser el enfrentamiento número " + j + ": " + atacante.getNombre() + " vs " + defensor.getNombre());

                    equiposGanadores.add(equipoGanador);
                    ganadoresJornada.add(equipoGanador);

                    sb.append("   - ").append(equipoGanador.getNombre()).append("\n");
                }

                equiposGanadoresJornada.add(ganadoresJornada);
                sb.append("\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Ganadores de Cada Jornada", JOptionPane.INFORMATION_MESSAGE);
        } while (correcto);
    }*/


    /**
     * Solicita un dato al usuario y lo valida contra una expresión regular.
     *
     * @param dato Nombre del dato que se solicita.
     * @param mensaje Mensaje que se muestra al usuario.
     * @param expresionRegular Expresión regular para validar el dato ingresado.
     * @return El dato ingresado y validado.
     * @throws DatoNoValido Si el dato no cumple con los requisitos.
     */

    public static String solicitarDato(String dato, String mensaje, String expresionRegular)
    {
        String variable = "";
        boolean error;
        do
        {
            try {
                variable = JOptionPane.showInputDialog(mensaje);
                if (variable.isEmpty())
                    throw new DatoNoValido(dato + " es un campo obligatorio");
                Pattern pat = Pattern.compile(expresionRegular);
                Matcher mat = pat.matcher(variable);
                if (!mat.matches())
                    throw new DatoNoValido(dato + " no tiene un formato adecuado");
                error = false;
            } catch (DatoNoValido e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        while(error);
        return variable;
    }

    public static LocalDate solicitarFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaS = JOptionPane.showInputDialog("Ingrese la fecha del enfrentamiento: ");
        LocalDate fecha = LocalDate.parse(fechaS, formatter);
        return fecha;
    }
    public static LocalTime solicitarHora(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaInput = JOptionPane.showInputDialog("Ingrese la hora de enfrentamiento: ");
        LocalTime hora= LocalTime.parse(horaInput, formatter);
        return hora;
    }
    /**
     * Crea un nuevo enfrentamiento y lo guarda en la base de datos.
     *
     * @param enfrentamiento Objeto `Enfrentamiento` que se desea crear.
     */
    public void crearEnfrentamiento(Enfrentamiento enfrentamiento) {
        dao.altaEnfrentamiento(enfrentamiento);
        listaEnfrentamientos.add(enfrentamiento);
    }

    public ArrayList<Enfrentamiento> rellenarEquiposEnfrentamientos(){
        return listaEnfrentamientos;
    }
}