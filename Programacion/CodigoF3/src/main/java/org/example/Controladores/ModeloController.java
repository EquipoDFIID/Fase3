package org.example.Controladores;

import org.example.Modelo.*;

import java.sql.Connection;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Random;

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

    public ArrayList <Equipo> selectNombreEquipo() {
        return equipoController.selectNombreEquipo();
    }
    public ArrayList <Jugador> selectNicknameJugador(){
        return jugadorController.selectNicknameJugador();
    }

    public Jugador buscarJugador(String nombreJugador) {
        return jugadorController.buscarJugador(nombreJugador);
    }
    public Equipo buscarEquipo(String nombreEquipo) {
        return equipoController.buscarEquipo(nombreEquipo);
    }

    public void altaEquipo(Equipo e){
        equipoController.altaEquipo(e);
    }
    public void modificarEquipo(Equipo e, String nombreEquipo) {
        equipoController.modificarEquipo(e, nombreEquipo);
    }
    public void bajaEquipo(Equipo e) {
        equipoController.bajaEquipo(e);
    }
    public void altaJugador(Jugador j){
         jugadorController.altaJugador(j);
    }
    public void bajaJugador( String nombreJugador) {
        jugadorController.bajaJugador( nombreJugador);
    }
    public void modificarJugador(Jugador j, String nombreJugador) {
        jugadorController.modificarJugador(j, nombreJugador);
    }
    public Usuario selectNombre(String nombreUsuario) {
        return usuarioController.selectNombre(nombreUsuario);
    }

    public void inscripcionCerrada(){
        int numeroEquipos = equipoController.selectCountEquipos() - 1;
        ArrayList<Equipo> equipos = equipoController.selectAllEquipos();
        for (int i = 0; i < numeroEquipos; i++) {
            jornadaController.crearJornada();
            for(int o = 0; o < numeroEquipos / 2; o++) {
                Equipo atacante = selecionarEquipoRandom(equipos);
                Equipo defensor = selecionarEquipoRandom(equipos);
                enfrentamientoController.crearEnfrentamiento(atacante,defensor);
            }

        }

    }

    private Equipo selecionarEquipoRandom(ArrayList<Equipo> equipos) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(equipos.size());

        // Obtener y remover el equipo seleccionado para evitar repeticiones
        Equipo equipoSeleccionado = equipos.remove(indiceAleatorio);

        return equipoSeleccionado;
    }

}
