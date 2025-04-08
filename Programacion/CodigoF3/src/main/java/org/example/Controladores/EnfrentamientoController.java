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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnfrentamientoController {
    public static ArrayList<Equipo> equiposGanadores=new ArrayList<>();
    public static List<Enfrentamiento> enfrentamientos=new ArrayList<>();

    public EnfrentamientoController(EnfrentamientoDAO enfrentamientoDAO) {
    }

    public void generarEnfrentamientos() {
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
    }


    public static Enfrentamiento solicitarValidarDatos() {
        String idEnfrentamiento = solicitarDato("ID Enfrentamiento", "Teclea el id del enfrentamiento", "^[0-9]{4}[A-Z]{1}$");
        LocalDate fechaEnfrentamiento = solicitarFecha();
        LocalTime horaEnfrentamiento = solicitarHora();


        Enfrentamiento e = new Enfrentamiento(idEnfrentamiento, fechaEnfrentamiento, horaEnfrentamiento, null, null, null, null);
        return e;
    }

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

    public Equipo preguntarResultados() {
        Equipo equipoGanador = null;
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            Equipo equipo1 = enfrentamiento.getEquipoAtacante();
            Equipo equipo2 = enfrentamiento.getEquipoDefensor();

            String[] opciones = {equipo1.getNombre(), equipo2.getNombre()};
            int resultado = JOptionPane.showOptionDialog(
                    null,
                    "¿Quién ha ganado?",
                    equipo1.getNombre() + " vs " + equipo2.getNombre(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );



            if (resultado == 0) {
                equipoGanador = equipo1;
            } else if (resultado == 1) {
                equipoGanador = equipo2;
            }


        }

        return equipoGanador;
    }



}