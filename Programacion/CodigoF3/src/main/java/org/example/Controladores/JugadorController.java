package org.example.Controladores;

import org.example.Modelo.Equipo;
import org.example.Modelo.Jugador;
import org.example.Modelo.JugadorDAO;

import java.time.LocalDate;
import java.util.ArrayList;
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



    public boolean altaJugador(String nombre, String apellido, String nacionalidad,
                            LocalDate fechaNacimiento, String nickname,
                            double sueldo, Equipo equipo) throws Exception {
        Jugador j = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
         return  jugadorDAO.altaJugador(j);
    }
    public boolean bajaJugador( String nombreJugador) throws Exception {
        return jugadorDAO.borrarJugador(nombreJugador);
    }
    public boolean modificarJugador(String nombre, String apellido, String nacionalidad,
                                 LocalDate fechaNacimiento, String nickname,
                                 double sueldo, Equipo ej) throws Exception {
        Jugador jugador = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
         return jugadorDAO.modificarJugador(jugador, jb);
    }
    public void buscarJugador(String nombreJugador) throws Exception {
        jb = jugadorDAO.buscarJugador(nombreJugador);
    }

    public ArrayList<Jugador> selectObjetosJugador() throws Exception {
        return jugadorDAO.selectObjetosJugador();
    }
}
