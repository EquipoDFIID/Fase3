package org.example.Controladores;

import org.example.Modelo.Equipo;
import org.example.Modelo.Jugador;
import org.example.Modelo.Usuario;
import org.example.Vista.VentanaAdministrador;
import org.example.Vista.VentanaAltaJugador;
import org.example.Vista.VentanaInicio;
import org.example.Vista.VentanaUsuario;

import javax.swing.*;
import java.time.LocalDate;
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

    public void altaEquipo(String nombre, String fecha){
        modeloController.altaEquipo(nombre, fecha);
    }
    public void bajaEquipo(String nombreEquipo){
        modeloController.buscarEquipo(nombreEquipo);
        modeloController.bajaEquipo();
    }
    public void modificarEquipo(String nombre, String fecha, String nombreEquipo){
        modeloController.buscarEquipo(nombreEquipo);
        modeloController.modificarEquipo(nombre, fecha);

    }

    public void altaJugador(String nombre, String apellido, String nacionalidad,
                            LocalDate fechaNacimiento, String nickname,
                            double sueldo, Equipo equipo) {
        modeloController.altaJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
    }
    public void bajaJugador(String nombreJugador){
        modeloController.buscarJugador(nombreJugador);
        modeloController.bajaJugador(nombreJugador);
    }
    public void modificarJugador(String nombre, String apellido, String nacionalidad,
                                 LocalDate fechaNacimiento, String nickname,
                                 double sueldo, Equipo ej, String nombreJugador){
        modeloController.buscarJugador(nombreJugador);
        modeloController.modificarJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
    }

    public Usuario selectNombre(String nombreUsuario) {
        return modeloController.selectNombre(nombreUsuario);
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

    public void crearCuenta(String nombre, String clave){
        modeloController.crearCuenta(nombre, clave);
    }
}
