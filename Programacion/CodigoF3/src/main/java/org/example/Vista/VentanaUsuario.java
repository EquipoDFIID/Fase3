package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

/**
 * Clase VentanaUsuario.
 * Ventana principal del usuario que muestra las opciones disponibles para consultar información.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaUsuario extends JFrame {
    private JPanel panel1;
    private JButton bLogo;
    private JButton resultadosDeÚltimaJornadaButton;
    private JButton informacionEquiposButton;
    private JMenuItem uNombre;
    private JMenuItem uCambiarCuenta;
    private JMenuItem uSalir;
    private JButton infoJugadores;
    private VistaController vc;

    public VentanaUsuario(VistaController vc, String uNombre) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,580);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        this.uNombre.setText(uNombre);
        this.uNombre.setEnabled(false);
        uCambiarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });

        uSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        informacionEquiposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaEquipos(uNombre, VentanaUsuario.this);
                dispose();
            }
        });

        resultadosDeÚltimaJornadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarVentanaResultados(uNombre, VentanaUsuario.this);
                dispose();
            }
        });
        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });
        infoJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.mostrarventanaJugadores(uNombre,VentanaUsuario.this);
                dispose();
            }
        });
    }
}
