package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Logger;

public class VentanaAdministrador extends JFrame {
    public JPanel panel1;
    private JButton bLogo;
    private JButton bAltaJugador;
    private JButton bModificarJugador;
    private JButton bBajaJugador;
    private JButton bAltaEquipo;
    private JButton bModificarEquipo;
    private JButton bBajaEquipo;
    private JButton bIntroducirResultados;
    private JButton bVerInformes;
    private JMenuItem jSalir;
    private JMenuItem jCambiarCuenta;
    private JMenuItem jNombre;
    private JButton bInscripcion;
    private static VistaController vc;
    private boolean estadoInscripcion;
    private static String aNombre;


    /**
     * Clase que representa la ventana principal del administrador.
     * Esta ventana permite al administrador realizar varias acciones como
     * dar de alta, modificar y dar de baja jugadores y equipos, así como
     * introducir resultados en la competición.
     */
    public VentanaAdministrador(VistaController vc, String aNombre) {
        //Set iniciales
        this.vc = vc;
        this.aNombre = aNombre;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        iconoVentana();

        inicializarCampos();
        agregarListeners();
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos(){
        jNombre.setText(aNombre);
        jNombre.setEnabled(false);
        bIntroducirResultados.setEnabled(false);
        bVerInformes.setEnabled(false);
    }

    public void agregarListeners(){
        jCambiarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });

        jSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bAltaJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaAltaJugador(aNombre, VentanaAdministrador.this);
            }
        });

        bModificarJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaModificarJugador(aNombre, VentanaAdministrador.this);
            }
        });

        bBajaJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaBajaJugador(aNombre, VentanaAdministrador.this);
            }
        });

        bAltaEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaAltaEquipo(aNombre, VentanaAdministrador.this);
            }
        });

        bModificarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaModificarEquipo(aNombre, VentanaAdministrador.this);
            }
        });

        bBajaEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaBajaEquipo(aNombre, VentanaAdministrador.this);
            }
        });

        bIntroducirResultados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaIntroducirResultados(aNombre, VentanaAdministrador.this);
            }
        });

        bVerInformes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaInformes(aNombre, VentanaAdministrador.this);
            }
        });

        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });
        bInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!estadoInscripcion) {
                        estadoInscripcion = vc.cerrarInscripcion();
                        disableCrud();
                        bIntroducirResultados.setEnabled(true);
                        bVerInformes.setEnabled(true);
                    } else {
                        String mensaje = "La inscripción ya ha sido cerrada";
                        JOptionPane.showMessageDialog(VentanaAdministrador.this, mensaje);
                    }
                } catch (Exception ex) {
                    String mensajeError = "Error al cerrar inscripción: " + ex.getMessage();
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, mensajeError);
                }
            }
        });
    }

    public void disableCrud(){
        bAltaJugador.setEnabled(false);
        bAltaEquipo.setEnabled(false);
        bModificarJugador.setEnabled(false);
        bModificarEquipo.setEnabled(false);
        bBajaJugador.setEnabled(false);
        bBajaEquipo.setEnabled(false);
    }
}
