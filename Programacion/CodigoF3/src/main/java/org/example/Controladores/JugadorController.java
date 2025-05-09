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


    /**
     *
     * @param nombre
     * @param apellido
     * @param nacionalidad
     * @param fechaNacimiento
     * @param nickname
     * @param sueldo
     * @param equipo
     * @return retorna un objeto jugador
     * @throws Exception
     */
    public boolean altaJugador(String nombre, String apellido, String nacionalidad,
                            LocalDate fechaNacimiento, String nickname,
                            double sueldo, Equipo equipo) throws Exception {
        Jugador j = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
         return  jugadorDAO.altaJugador(j);
    }

    /**
     *
     * @param nombreJugador
     * @return retorna booleano de si hemos dado de baja a un jugador o no
     * @throws Exception
     */
    public boolean bajaJugador( String nombreJugador) throws Exception {
        return jugadorDAO.borrarJugador(nombreJugador);
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param nacionalidad
     * @param fechaNacimiento
     * @param nickname
     * @param sueldo
     * @param ej
     * @return retorna un booleano de si hemos modificado un jugador
     * @throws Exception
     */
    public boolean modificarJugador(String nombre, String apellido, String nacionalidad,
                                 LocalDate fechaNacimiento, String nickname,
                                 double sueldo, Equipo ej) throws Exception {
        Jugador jugador = new Jugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
         return jugadorDAO.modificarJugador(jugador, jb);
    }

    /**
     *
     * @param nombreJugador
     * @throws Exception
     */
    public void buscarJugador(String nombreJugador) throws Exception {
        jb = jugadorDAO.buscarJugador(nombreJugador);
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Jugador> selectObjetosJugador() throws Exception {
        return jugadorDAO.selectObjetosJugador();
    }
}
