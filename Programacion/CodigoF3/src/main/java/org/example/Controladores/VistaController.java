package org.example.Controladores;

import org.example.Modelo.*;
import org.example.Vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
    private VentanaJugador ventanaJugador;

    private ModeloController modeloController;
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private ArrayList<Jornada> listaJornadas = new ArrayList<>();
    private ArrayList<Enfrentamiento> listaEnfrentamientos = new ArrayList<>();
    private  ArrayList<JRadioButton> botonesEquipos = new ArrayList<>();
    private ArrayList<ButtonGroup > gruposEquipos = new ArrayList<>(2);

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
        ventanaCuenta = new VentanaCrearCuenta(this, ventanaInicio);
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



    public boolean altaEquipo(String nombre, LocalDate fecha) throws Exception{
        return modeloController.altaEquipo(nombre, fecha);
    }
    public boolean bajaEquipo(String nombreEquipo) throws Exception{
        modeloController.buscarEquipo(nombreEquipo);
        return modeloController.bajaEquipo();
    }
    public boolean modificarEquipo(String nombre, LocalDate fecha, String nombreEquipo) throws Exception{
        modeloController.buscarEquipo(nombreEquipo);
        return modeloController.modificarEquipo(nombre, fecha);
    }


    public boolean altaJugador(String nombre, String apellido, String nacionalidad,
                            LocalDate fechaNacimiento, String nickname,
                            double sueldo, Equipo equipo) throws Exception {
        return modeloController.altaJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
    }
    public boolean bajaJugador(String nombreJugador) throws Exception{
        modeloController.buscarJugador(nombreJugador);
        return modeloController.bajaJugador(nombreJugador);
    }
    public boolean modificarJugador(String nombre, String apellido, String nacionalidad,
                                 LocalDate fechaNacimiento, String nickname,
                                 double sueldo, Equipo ej, String nombreJugador) throws Exception {
        modeloController.buscarJugador(nombreJugador);
        return modeloController.modificarJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
    }



    public void selectUsuarioNick(String nickUsuario, String clave) throws Exception {
        modeloController.selectUsuarioNick(nickUsuario, clave);
    }
    public void selectUsuarioNom(String nombreUsuario, String clave) throws Exception {
        modeloController.selectUsuarioNom(nombreUsuario, clave);
    }
    public boolean comprobarNombreClave(String tipo) {
        return  modeloController.comprobarNombreClave(tipo);
    }

    public void rellenarEquipos() throws Exception{
        listaEquipos = modeloController.selectObjetosEquipo();
    }
    public void rellenarJugadores() throws Exception{
        listaJugadores = modeloController.selectObjetosJugador();
    }
    public void rellenarJornadas() {
        listaJornadas = modeloController.selectObjetosJornada();
    }
    public void rellenarEquiposEnfrentamientos(JPanel panelEquipos, int indexJornada, JButton bAceptar, VentanaIntroducirResultados ventana) {
        panelEquipos.setEnabled(false);
        panelEquipos.removeAll();
        gruposEquipos.clear(); // Asegúrate de vaciar los anteriores
        botonesEquipos.clear();

        if (indexJornada > 0 && indexJornada <= modeloController.jornadas.size()) {
            panelEquipos.setEnabled(true);

            Jornada jornadaSeleccionada = modeloController.jornadas.get(indexJornada - 1);
            List<Enfrentamiento> enfrentamientos = jornadaSeleccionada.getListaEnfrentamientos();

            for (Enfrentamiento enf : enfrentamientos) {
                JRadioButton bAtacante = new JRadioButton(enf.getEquipoAtacante().getNombre());
                JRadioButton bDefensor = new JRadioButton(enf.getEquipoDefensor().getNombre());
                ButtonGroup grupo = new ButtonGroup();

                bAtacante.setBackground(Color.white);
                bDefensor.setBackground(Color.white);

                // Agrega el listener aquí
                ActionListener listener = e -> bAceptar.setEnabled(ventana.comprobarSelecciones());

                bAtacante.addActionListener(listener);
                bDefensor.addActionListener(listener);

                grupo.add(bAtacante);
                grupo.add(bDefensor);
                gruposEquipos.add(grupo);

                panelEquipos.add(bAtacante);
                panelEquipos.add(bDefensor);
            }

            panelEquipos.revalidate();
            panelEquipos.repaint();
        }
    }

    public ArrayList<ButtonGroup> pasarGrupos() {
        return gruposEquipos;
    }

    public boolean cerrarInscripcion() throws Exception {
        return  modeloController.cerrarInscripcion();
    }

    public void llenarComboBoxE(JComboBox jEquipo) throws Exception {
        rellenarEquipos();
        jEquipo.removeAllItems();
        jEquipo.addItem("Selecciona un equipo...");
        for (Equipo equipo : listaEquipos) {
            //getIndex
            jEquipo.addItem(equipo.getNombre());
        }
        jEquipo.setSelectedIndex(0);
    }

    public void llenarComboBoxJ(JComboBox jJugador) throws Exception {
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

    public boolean procesarGanadoresSeleccionados(int indexJornadaSeleccionada) throws Exception {
        Jornada jornada = modeloController.jornadas.get(indexJornadaSeleccionada - 1);
        List<Enfrentamiento> enfrentamientos = jornada.getListaEnfrentamientos();

        for (int i = 0; i < gruposEquipos.size(); i++) {
            ButtonGroup grupo = gruposEquipos.get(i);
            Enfrentamiento enfrentamiento = enfrentamientos.get(i);

            Enumeration<AbstractButton> botones = grupo.getElements();
            while (botones.hasMoreElements()) {
                AbstractButton boton = botones.nextElement();
                if (boton.isSelected()) {
                    String nombreEquipoGanador = boton.getText();
                    if (enfrentamiento.getEquipoAtacante().getNombre().equals(nombreEquipoGanador)) {
                        enfrentamiento.setEquipoGanador(enfrentamiento.getEquipoAtacante());
                    } else {
                        enfrentamiento.setEquipoGanador(enfrentamiento.getEquipoDefensor());
                    }
                    break;
                }
            }
        }

        // Lista con IDs (viene de BD)
        ArrayList<Enfrentamiento> enfren = modeloController.selectEnfrentamientosJornada(jornada.getIdJornada());

        // Sincroniza equipoGanador desde enfrentamientos (interfaz) hacia enfren (BD)
        for (int i = 0; i < enfren.size(); i++) {
            Enfrentamiento enfrentamientoBD = enfren.get(i);
            Enfrentamiento enfrentamientoGUI = enfrentamientos.get(i);

            enfrentamientoBD.setEquipoGanador(enfrentamientoGUI.getEquipoGanador());
        }

        // Ahora enfren tiene id + equipoGanador
        return modeloController.asignarGanadoresEnfrentamientos(enfren);
    }



    public boolean crearCuenta(String nickname, String nombre, String clave) throws Exception{
        return modeloController.crearCuenta(nickname, nombre, clave);
    }

    public boolean comprobarNickname(String nickname) throws Exception {
        return modeloController.comprobarNickname(nickname);
    }

    public void mostrarventanaJugadores(String uNombre, VentanaUsuario ventanaUsuario) {
        ventanaJugador = new VentanaJugador(this, uNombre, ventanaUsuario);
        ventanaJugador.setVisible(true);
    }


    public String mostrarProcedimientoResultado() throws Exception {
        return modeloController.mostrarProcedimientoResultado();
    }
}
