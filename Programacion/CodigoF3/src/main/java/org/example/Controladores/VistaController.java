package org.example.Controladores;

import org.example.Modelo.Equipo;
import org.example.Modelo.Jugador;
import org.example.Modelo.Usuario;
import org.example.Vista.VentanaAdministrador;
import org.example.Vista.VentanaAltaJugador;
import org.example.Vista.VentanaInicio;
import org.example.Vista.VentanaUsuario;

import javax.swing.*;
import java.util.ArrayList;

public class VistaController {
    private VentanaAdministrador ventanaAdministrador;
    private VentanaInicio ventanaInicio;
    private VentanaUsuario ventanaUsuario;
    private ModeloController modeloController;
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();

    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        ventanaInicio = new VentanaInicio(this);
        ventanaInicio.setVisible(true);
    }

    public void mostrarVentanaInicio(){
        ventanaInicio = new VentanaInicio(this);
        ventanaInicio.setVisible(true);
    }

    public void altaEquipo(Equipo e){
         modeloController.altaEquipo(e);
    }
    public void bajaEquipo(String nombreEquipo){
        Equipo e=modeloController.buscarEquipo(nombreEquipo);
        if(e!=null) modeloController.bajaEquipo(e);
    }
    public void modificarEquipo(Equipo equipo, String nombreEquipo){
        Equipo equipoAnterior = modeloController.buscarEquipo(nombreEquipo);
        if(equipoAnterior!=null){
            modeloController.modificarEquipo(equipo, equipoAnterior);
        }

    }
    public void altaJugador(Jugador j){
        modeloController.altaJugador(j);
    }
    public Jugador buscarJugador(String nombreJugador) {
        return modeloController.buscarJugador(nombreJugador);

    }
    public Usuario selectNombre(String nombreUsuario) {
        return modeloController.selectNombre(nombreUsuario);
    }


    public void bajaJugador(String nombreJugador){
        Jugador j=modeloController.buscarJugador(nombreJugador);
        if(j!=null)
            modeloController.bajaJugador(nombreJugador);
    }

    public void modificarJugador(Jugador jugador, String nombreJugador){
        Jugador jugadorAnterior = modeloController.buscarJugador(nombreJugador);
        if(jugadorAnterior!=null)
            modeloController.modificarJugador(jugador, jugadorAnterior);
    }

    public void generarJornada(){}

    public void generarEnfrentamientos(){}

    public void rellenarEquipos(){
        listaEquipos = modeloController.selectObjetosEquipo();
    }
    public void rellenarJugadores(){
        listaJugadores = modeloController.selectObjetosJugador();
    }

   /* public void prueba(){
       ArrayList<Equipo>equipos= modeloController.selectNombreEquipo();
       equipos=new ArrayList<>();
       Equipo equipo = null;
       equipos.add(equipo);
    }*/

    public void llenarComboBoxE(JComboBox jEquipo) {
        rellenarEquipos();
        jEquipo.removeAllItems();
        jEquipo.addItem("Selecciona un equipo...");
        for (Equipo equipo : listaEquipos) {
            //getIndex
            jEquipo.addItem(equipo.getNombre());
        }
        jEquipo.setSelectedIndex(0);
    }

    public void llenarComboBoxJ(JComboBox jJugador) {
        rellenarJugadores();
        jJugador.removeAllItems();
        jJugador.addItem("Selecciona un jugador...");
        for (Jugador jugador : listaJugadores) {
            //getIndex
            jJugador.addItem(jugador.getNickname());
        }
        jJugador.setSelectedIndex(0);
    }

    public Equipo buscarComboBoxE(JComboBox jEquipo) {
        int posicion = jEquipo.getSelectedIndex();
        return listaEquipos.get(posicion - 1);
    }

    public void crearCuenta(Usuario usuario){
        modeloController.crearCuenta(usuario);
    }
}
