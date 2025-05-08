package org.example.Controladores;

import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Equipo;
import org.example.Modelo.EquipoDAO;
import org.example.Modelo.Jugador;
import org.example.Modelo.JugadorDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Clase `UsuarioController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los usuarios, interactuando con la capa de datos (`UsuarioDAO`).
 */
public class JugadorController {
    private JugadorDAO jugadorDAO;
    private Jugador jb;
    public JugadorController(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }



    public void altaJugador(String nombre, String apellido, String nacionalidad,
                            LocalDate fechaNacimiento, String nickname,
                            double sueldo, Equipo equipo) throws Exception {
        Jugador j = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
         jugadorDAO.altaJugador(j);
    }
    public void bajaJugador( String nombreJugador) throws Exception {
        jugadorDAO.borrarJugador(nombreJugador);
    }
    public void modificarJugador(String nombre, String apellido, String nacionalidad,
                                 LocalDate fechaNacimiento, String nickname,
                                 double sueldo, Equipo ej) throws Exception {
        Jugador jugador = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
        jugadorDAO.modificarJugador(jugador, jb);
    }
    public void buscarJugador(String nombreJugador) throws Exception {
        jb = jugadorDAO.buscarJugador(nombreJugador);
    }

    public ArrayList<Jugador> selectObjetosJugador() throws Exception {
        return jugadorDAO.selectObjetosJugador();
    }


    /*

       public static Jugador solicitarValidarDatos() {
        // Copia de la versión anterior.
        String idJugador = solicitarDato("ID Jugador", "Teclea ID del jugador", "^[0-9]{4}[A-Z]{1}$");
        String nombre = solicitarDato("Nombre", "Teclea el nombre del jugador", "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$");
        String apellido = solicitarDato("Apellido","Teclea el apellido del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$");
        String nacionalidad = solicitarDato("Nacionalidad","Teclea la nacionalidad del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]*$");
        LocalDate fechaNacimiento = solicitarFecha();
        String nickname = solicitarDato("Nickname","Teclea el nickname del jugador","^[a-zA-Z0-9_]{4,}$");
        String rol = solicitarDato("Rol","Teclea el rol del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]{3,}$");
        Double sueldo = Double.valueOf(solicitarDato("Sueldo", "Tecle el sueldo del jugador", "^[0-9]{5}$"));
        String idEquipo = solicitarDato("ID", "Teclea el ID del equipo al que pertenece", "^[A-Z]{1}[0-9]{3}$");
        Equipo equipo = EquipoDAO.buscarEquipo(idEquipo);

        Jugador j = new Jugador(idJugador, nombre, apellido, nacionalidad, fechaNacimiento, nickname, rol, sueldo, equipo);
        return j;
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
        String fechaS = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento: ");
        LocalDate fecha = LocalDate.parse(fechaS, formatter);
        return fecha;
    }


    public static void altaJugador() {
        boolean continuar = false;
        do {
            try {
                Jugador jugador = solicitarValidarDatos();
                JugadorDAO.agregarJugador(jugador);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (continuar);
    }

    public static void bajaJugador(){
        boolean continuar = false;
        do {
            try{
                String idJugador = JOptionPane.showInputDialog("Ingrese el id del jugador a borrar");
                EquipoDAO.buscarJugador(idJugador);

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        while(!continuar);

    }

    public static Jugador modificarJugador() {
        boolean continuar = false;
        Jugador j=null;
        do {
            try {
                String idJugador = JOptionPane.showInputDialog("Introduzca el id del jugador a modificar");
                Jugador jugadorM = EquipoDAO.buscarJugador(idJugador);
                String nombre = solicitarDato("Nombre", "Teclea el nombre del jugador", "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$");
                String apellido = solicitarDato("Apellido","Teclea el apellido del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$");
                String nacionalidad = solicitarDato("Nacionalidad","Teclea la nacionalidad del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]*$");
                LocalDate fechaNacimiento = solicitarFecha();
                String nickname = solicitarDato("Nickname","Teclea el nickname del jugador","^[a-zA-Z0-9_]{4,}$");
                String rol = solicitarDato("Rol","Teclea el rol del jugador","^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]{3,}$");
                Double sueldo = Double.valueOf(solicitarDato("Sueldo", "Tecle el sueldo del jugador", "^[0-9]{5}$"));
                String idEquipo = solicitarDato("ID", "Teclea el ID del equipo al que pertenece", "^[A-Z]{1}[0-9]{3}$");
                Equipo equipo = EquipoDAO.buscarEquipo(idEquipo);
                j = new Jugador(idJugador, nombre, apellido, nacionalidad, fechaNacimiento, nickname, rol, sueldo, equipo);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al modificar el jugador");
            }
        } while (continuar);
        return j;
    } */


}
