package org.example.Controladores;

import org.example.Modelo.*;
import org.example.Vista.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controlador de la vista que gestiona la interacción entre las ventanas y el modelo.
 */
public class VistaController {
    private VentanaInicio ventanaInicio;
    private VentanaAdministrador ventanaAdministrador;
    private VentanaUsuario ventanaUsuario;
    private VentanaCrearCuenta ventanaCuenta;
    private VentanaAltaJugador ventanaAltaJugador;
    private VentanaAltaEquipo ventanaAltaEquipo;
    private VentanaBajaJugador ventanaBajaJugador;
    private VentanaBajaEquipo ventanaBajaEquipo;
    private VentanaModificarJugador ventanaModificarJugador;
    private VentanaModificarEquipo ventanaModificarEquipo;
    private VentanaInformes ventanaInformes;
    private VentanaIntroducirResultados ventanaIntroducirResultados;
    private VentanaEquipos ventanaEquipos;
    private VentanaResultados ventanaResultados;

    private ModeloController modeloController;
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private ArrayList<Jornada> listaJornadas = new ArrayList<>();
    private ArrayList<Enfrentamiento> listaEnfrentamientos = new ArrayList<>();

    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        ventanaInicio = new VentanaInicio(this);
        ventanaInicio.setVisible(true);
    }





    public void mostrarVentanaInicio(){
        ventanaInicio = new VentanaInicio(this);
        ventanaInicio.setVisible(true);
    }
    public void mostrarVentanaAdministrador(String aNombre){
        ventanaAdministrador = new VentanaAdministrador(this, aNombre);
        ventanaAdministrador.setVisible(true);
    }
    public void mostrarVentanaUsuario(String aNombre){
        ventanaUsuario = new VentanaUsuario(this, aNombre);
        ventanaUsuario.setVisible(true);
    }
    public void mostrarVentanaCuenta(JFrame ventanaInicio) {
        ventanaInicio.setVisible(false);
        ventanaCuenta = new VentanaCrearCuenta(this);
        ventanaCuenta.setVisible(true);
    }

    public void mostrarVentanaAltaJugador(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaAltaJugador = new VentanaAltaJugador(this, aNombre, ventanaAdmin);
        ventanaAltaJugador.setVisible(true);
    }

    public void mostrarVentanaAltaEquipo(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaAltaEquipo = new VentanaAltaEquipo(this, aNombre, ventanaAdmin);
        ventanaAltaEquipo.setVisible(true);
    }

    public void mostrarVentanaBajaJugador(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaBajaJugador = new VentanaBajaJugador(this, aNombre, ventanaAdmin);
        ventanaBajaJugador.setVisible(true);
    }

    public void mostrarVentanaBajaEquipo(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaBajaEquipo = new VentanaBajaEquipo(this, aNombre, ventanaAdmin);
        ventanaBajaEquipo.setVisible(true);
    }

    public void mostrarVentanaModificarJugador(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaModificarJugador = new VentanaModificarJugador(this, aNombre, ventanaAdmin);
        ventanaModificarJugador.setVisible(true);
    }

    public void mostrarVentanaModificarEquipo(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaModificarEquipo = new VentanaModificarEquipo(this, aNombre, ventanaAdmin);
        ventanaModificarEquipo.setVisible(true);
    }

    public void mostrarVentanaInformes(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaInformes = new VentanaInformes(this, aNombre, ventanaAdmin);
        ventanaInformes.setVisible(true);
    }

    public void mostrarVentanaIntroducirResultados(String aNombre, JFrame ventanaAdmin) {
        ventanaAdmin.setVisible(false);
        ventanaIntroducirResultados = new VentanaIntroducirResultados(this, aNombre, ventanaAdmin);
        ventanaIntroducirResultados.setVisible(true);
    }



    public void mostrarVentanaEquipos(String uNombre, JFrame ventanaUsuario) {
        ventanaEquipos = new VentanaEquipos(this, uNombre, ventanaUsuario);
        ventanaEquipos.setVisible(true);
    }

    public void mostrarVentanaResultados(String uNombre, JFrame ventanaUsuario) {
        ventanaResultados = new VentanaResultados(this, uNombre, ventanaUsuario);
        ventanaResultados.setVisible(true);
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
    public void rellenarJornadas(){
        listaJornadas = modeloController.selectObjetosJornada();
    }
    public void rellenarEquiposEnfrentamientos(JRadioButton bEquipo1, JRadioButton bEquipo2, JRadioButton bEquipo3, JRadioButton bEquipo4, JRadioButton bEquipo5, JRadioButton bEquipo6, JRadioButton bEquipo7, JRadioButton bEquipo8, JRadioButton bEquipo9, JRadioButton bEquipo10) {
        listaEnfrentamientos = modeloController.rellenarEquiposEnfrentamientos();

        if (listaEnfrentamientos.size() >= 5) {
            bEquipo1.setText(listaEnfrentamientos.get(0).getEquipoAtacante().getNombre());
            bEquipo2.setText(listaEnfrentamientos.get(0).getEquipoDefensor().getNombre());

            bEquipo3.setText(listaEnfrentamientos.get(1).getEquipoAtacante().getNombre());
            bEquipo4.setText(listaEnfrentamientos.get(1).getEquipoDefensor().getNombre());

            bEquipo5.setText(listaEnfrentamientos.get(2).getEquipoAtacante().getNombre());
            bEquipo6.setText(listaEnfrentamientos.get(2).getEquipoDefensor().getNombre());

            bEquipo7.setText(listaEnfrentamientos.get(3).getEquipoAtacante().getNombre());
            bEquipo8.setText(listaEnfrentamientos.get(3).getEquipoDefensor().getNombre());

            bEquipo9.setText(listaEnfrentamientos.get(4).getEquipoAtacante().getNombre());
            bEquipo10.setText(listaEnfrentamientos.get(4).getEquipoDefensor().getNombre());
        }
    }

    public boolean cerrarInscripcion(){
        return  modeloController.cerrarInscripcion();
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

    public void llenarComboBoxJor(JComboBox jJornada) {
        rellenarJornadas();
        jJornada.removeAllItems();
        jJornada.addItem("Selecciona una jornada...");
        for (Jornada jornada : listaJornadas) {
            jJornada.addItem(jornada.getIdJornada());
        }
        jJornada.setSelectedIndex(0);
    }

    public void crearCuenta(String nombre, String clave){
        modeloController.crearCuenta(nombre, clave);
    }
}
