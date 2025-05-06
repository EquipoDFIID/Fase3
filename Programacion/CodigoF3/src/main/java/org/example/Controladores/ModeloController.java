package org.example.Controladores;

import org.example.Modelo.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class ModeloController {

    private CampeonatoDao campeonatoDao;
    private CampeonatoController campeonatoController;

    private EnfrentamientoController enfrentamientoController;
    private EnfrentamientoDAO enfrentamientoDao;

    private EquipoController equipoController;
    private EquipoDAO equipoDao;

    private JornadaController jornadaController;
    private JornadaDAO jornadaDao;

    private JugadorController jugadorController;
    private JugadorDAO jugadorDao;

    private UsuarioController usuarioController;
    private UsuarioDAO usuarioDao;

    private VistaController vc;

    public ModeloController() {
        try{
            //BaseDatos.abrirConexion();
            Connection con =BD.getConnection();

            campeonatoDao=new CampeonatoDao();
            campeonatoController= new CampeonatoController(campeonatoDao);

            enfrentamientoDao=new EnfrentamientoDAO();
            enfrentamientoController=new EnfrentamientoController(enfrentamientoDao);

            equipoDao=new EquipoDAO();
            equipoController=new EquipoController(equipoDao);

            jornadaDao=new JornadaDAO();
            jornadaController=new JornadaController(jornadaDao);

            jugadorDao=new JugadorDAO();
            jugadorController=new JugadorController(jugadorDao);

            usuarioDao=new UsuarioDAO();
            usuarioController=new UsuarioController(usuarioDao);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setVista(VistaController vc) {
        this.vc=vc;
    }

    public ArrayList <Equipo> selectObjetosEquipo() {
        return equipoController.selectObjetosEquipo();
    }
    public ArrayList <Jugador> selectObjetosJugador() {return jugadorController.selectObjetosJugador();
    }

    public void buscarJugador(String nombreJugador) {jugadorController.buscarJugador(nombreJugador);
    }
    public void buscarEquipo(String nombreEquipo) {equipoController.buscarEquipo(nombreEquipo);
    }

    public void altaEquipo(String nombre, String fecha){
        equipoController.altaEquipo(nombre, fecha);
    }
    public void bajaEquipo() {
        equipoController.bajaEquipo();
    }
    public void modificarEquipo(String nombre, String fecha) {
        equipoController.modificarEquipo(nombre, fecha);
    }

    public void altaJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo equipo){
         jugadorController.altaJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
    }
    public void bajaJugador( String nombreJugador) {
        jugadorController.bajaJugador( nombreJugador);
    }
    public void modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo ej) {
        jugadorController.modificarJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
    }



    public Usuario selectNombre(String nombreUsuario) {
        return usuarioController.selectNombre(nombreUsuario);
    }

    public void inscripcionCerrada() {
        ArrayList<Equipo> equiposOriginal = equipoController.selectAllEquipos();
        int numeroEquipos = equiposOriginal.size(); // siempre par

        int totalJornadas = numeroEquipos - 1;
        int partidosPorJornada = numeroEquipos / 2;

        // Creamos una lista mutable para rotar (sin afectar la original)
        ArrayList<Equipo> equipos = new ArrayList<>(equiposOriginal);

        for (int jornada = 0; jornada < totalJornadas; jornada++) {
            ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<>();
            Jornada jornadaNueva = new Jornada(LocalDate.now(),2,enfrentamientos);
            Jornada jornadaEnfren = jornadaController.crearJornada(jornadaNueva);
            System.out.println("Jornada " + (jornada + 1) + " creada");

            for (int i = 0; i < partidosPorJornada; i++) {
                Equipo local = equipos.get(i);
                Equipo visitante = equipos.get(numeroEquipos - 1 - i);
                ArrayList<Equipo> generarGnador = new ArrayList<>();
                generarGnador.add(local);
                generarGnador.add(visitante);
                Collections.shuffle(generarGnador);
                Equipo ganador = generarGnador.get(0);
                Enfrentamiento enfrentamiento = new Enfrentamiento(LocalDate.now(), LocalTime.now(), local, visitante, jornadaEnfren, ganador);
                enfrentamientos.add(enfrentamiento);
                enfrentamientoController.crearEnfrentamiento(enfrentamiento);
                System.out.println(local.getNombre() + " vs " + visitante.getNombre());
            }

            // Rotación: el primer equipo queda fijo, los demás giran a la derecha
            Equipo fijo = equipos.get(0);
            equipos.remove(0);
            Equipo ultimo = equipos.remove(equipos.size() - 1);
            equipos.add(0, ultimo); // el nuevo segundo
            equipos.add(0, fijo);   // el primero se mantiene fijo
        }
    }

    public void crearCuenta(String nombre, String clave){
            usuarioController.crearCuenta(nombre, clave);
    }

    public ArrayList selectAllEquipos() {
        return equipoController.selectAllEquipos();
    }
}
