package org.example.Controladores;

import org.example.Modelo.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Clase `UsuarioController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los usuarios, interactuando con la capa de datos (`UsuarioDAO`).
 */
public class ModeloController {

    /**
     * Creacion de los dao y controller de las clases que tenemos
     */


    private CampeonatoDAO campeonatoDao;
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
    private Enfrentamiento enfrentamiento;
    private Equipo ganador;


    ArrayList <Jornada> jornadas = new ArrayList<>();

    private Usuario usuario;
    private String tipoUsuario;

    public ModeloController() {
        try{
            //BaseDatos.abrirConexion();
            Connection con =BD.getConnection();

            campeonatoDao=new CampeonatoDAO();
            campeonatoController= new CampeonatoController(campeonatoDao);

            jornadaDao=new JornadaDAO();
            jornadaController=new JornadaController(jornadaDao);

            enfrentamientoDao=new EnfrentamientoDAO();
            enfrentamientoController=new EnfrentamientoController(enfrentamientoDao);

            equipoDao=new EquipoDAO();
            equipoController=new EquipoController(equipoDao);

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

    /**
     * Selecciones de objetos
     * @return
     * @throws Exception
     */
    public ArrayList <Equipo> selectObjetosEquipo() throws Exception {
        return equipoController.selectObjetosEquipo();
    }
    public ArrayList <Jugador> selectObjetosJugador() throws Exception {return jugadorController.selectObjetosJugador();
    }
    public ArrayList <Jornada> selectObjetosJornada() {
        return jornadaController.selectObjetosJornada();
    }

    public ArrayList <Enfrentamiento> rellenarEquiposEnfrentamientos() throws Exception {
        return enfrentamientoController.rellenarEquiposEnfrentamientos();
    }

    /**
     * Búsqueda de objetos
     * @param nombreJugador
     * @throws Exception
     */
    public void buscarJugador(String nombreJugador) throws Exception {jugadorController.buscarJugador(nombreJugador);
    }
    public void buscarEquipo(String nombreEquipo) throws Exception {equipoController.buscarEquipo(nombreEquipo);
    }

    /**
     * Altas, bajas y modficaciones
     * @param nombre
     * @param fecha
     * @return
     * @throws Exception
     */
    public boolean altaEquipo(String nombre, LocalDate fecha) throws Exception{
        return equipoController.altaEquipo(nombre, fecha);
    }
    public boolean bajaEquipo() throws Exception {
        return equipoController.bajaEquipo();
    }
    public boolean modificarEquipo(String nombre, LocalDate fecha) throws Exception {
        return equipoController.modificarEquipo(nombre, fecha);
    }

    public boolean altaJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo equipo) throws Exception {
        return jugadorController.altaJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
    }
    public boolean bajaJugador( String nombreJugador) throws Exception {
        return jugadorController.bajaJugador( nombreJugador);
    }
    public boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo ej) throws Exception {
        return jugadorController.modificarJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
    }


    /**
     * Consultas de usuario
     * @param nickUsuario
     * @param clave
     * @throws Exception
     */
    public void selectUsuarioNick(String nickUsuario, String clave) throws Exception {
        usuario = usuarioController.selectUsuarioNick(nickUsuario, clave);
    }
    public void selectUsuarioNom(String nombreUsuario, String clave) throws Exception {
        usuario = usuarioController.selectUsuarioNom(nombreUsuario, clave);
    }

    /**
     * Comprobacion de la clave
     * @param tipo
     * @return
     */
    public boolean comprobarNombreClave(String tipo){
        boolean encontrado=false;

        if (usuario != null){
            if (tipo.equals(usuario.getTipoUsuario())){
                encontrado = true;
            }
        }

        return encontrado;
    }

    /**
     * Cierre de inscripcion
     * @return
     * @throws Exception
     */
    public boolean cerrarInscripcion() throws Exception {
        vc.resetDatos();

        competicionUpdateInscripcion("en curso");
        boolean cerrada = false;
        ArrayList<Equipo> equiposOriginal = equipoController.selectAllEquipos();
        int numeroEquipos = equiposOriginal.size(); // siempre par
        int totalJornadas = numeroEquipos - 1;
        int partidosPorJornada = numeroEquipos / 2;

        // Creamos una lista mutable para rotar (sin afectar la original)
        ArrayList<Equipo> equipos = new ArrayList<>(equiposOriginal);

        // Fecha base - hoy para la primera jornada
        LocalDate fechaJornada = LocalDate.now();

        for (int jornada = 0; jornada < totalJornadas; jornada++) {
            ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<>();
            // Crear jornada con la fecha calculada
            Jornada jornadaNueva = new Jornada(enfrentamientos, fechaJornada, campeonatoController.buscarCompeticion(1));
            jornadas.add(jornadaNueva);
            Jornada jornadaEnfren = jornadaController.crearJornada(jornadaNueva);
            //System.out.println("Jornada " + (jornada + 1) + " creada para el " + fechaJornada);

            // Hora inicial - 9:00 AM para el primer enfrentamiento
            LocalTime horaEnfrentamiento = LocalTime.of(9, 0);

            for (int i = 0; i < partidosPorJornada; i++) {
                Equipo local = equipos.get(i);
                Equipo visitante = equipos.get(numeroEquipos - 1 - i);

                // Crear enfrentamiento con fecha de jornada y hora calculada
                Enfrentamiento enfrentamiento = new Enfrentamiento(
                        fechaJornada,
                        horaEnfrentamiento,
                        local,
                        visitante,
                        jornadaEnfren
                );
                enfrentamientos.add(enfrentamiento);
                /*System.out.println(local.getNombre() + " vs " + visitante.getNombre() +
                        " a las " + horaEnfrentamiento);*/

                // Sumar 2 horas para el próximo enfrentamiento
                horaEnfrentamiento = horaEnfrentamiento.plusHours(2);
            }

            enfrentamientoController.crearEnfrentamientos(enfrentamientos);

            // Rotación de equipos
            Equipo fijo = equipos.get(0);
            equipos.remove(0);
            Equipo ultimo = equipos.remove(equipos.size() - 1);
            equipos.add(0, ultimo); // el nuevo segundo
            equipos.add(0, fijo); // el primero se mantiene fijo

            // Sumar 1 semana para la próxima jornada
            fechaJornada = fechaJornada.plusWeeks(1);
            cerrada = true;
        }
        return cerrada;
    }

    /**
     * Creacion de la cuenta, pasandolo a usuarioController
     * @param nickname
     * @param nombre
     * @param clave
     * @return
     * @throws Exception
     */
    public boolean crearCuenta(String nickname, String nombre, String clave) throws Exception {
        return usuarioController.crearCuenta(nickname, nombre, clave);
    }

    public void competicionUpdateInscripcion(String inscripcion) throws Exception {
        campeonatoController.competicionUpdateInscripcion(inscripcion);
    }

    public boolean comprobarNickname(String nickname) throws Exception {
        return usuarioController.comprobarNickname(nickname);
    }

    /**
     * Metodo para asignar el equipo ganador
     * @param enfrentamientos
     * @return
     * @throws Exception
     */
    public boolean asignarGanadoresEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) throws Exception {
        boolean todosAsignados = true;

        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            boolean asignado = enfrentamientoController.asignarGanadorEnfrentamiento(enfrentamiento);
            if (!asignado) {
                todosAsignados = false;
            }
        }

        return todosAsignados;
    }

    public ArrayList<Enfrentamiento> selectEnfrentamientosJornada(int idJornada) throws Exception{
        return enfrentamientoController.selectEnfrentamientosJornada(idJornada);
    }

    public String mostrarProcedimientoResultado() throws Exception {
        return enfrentamientoController.mostrarProcedimientoResultado();
    }

    public boolean hayJornadasAnterioresSinResultados(int idJornadaActual) throws Exception {
        return jornadaController.hayJornadasAnterioresSinResultados(idJornadaActual);
    }

    public void resetDatos() throws Exception {
        jornadaController.resetDatos();
    }
}
