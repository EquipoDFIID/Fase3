package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdministrador extends JFrame {
    public JPanel panel1;
    private JButton button1;
    private JButton altaJugadorButton;
    private JButton modificarJugadorButton;
    private JButton bajaJugadorButton;
    private JButton altaEquipoButton;
    private JButton modificarEquipoButton;
    private JButton bajaEquipoButton;
    private JButton introducirResultadosButton;
    private static VistaController vc;

    public VentanaAdministrador(VistaController vc) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,550);
        setLocationRelativeTo(null);

        altaJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAltaJugador ventanaAltaJugador = new VentanaAltaJugador(vc);
                ventanaAltaJugador.setVisible(true);
            }
        });
        modificarJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaModificarJugador ventanaModificarJugador = new VentanaModificarJugador(vc);
                ventanaModificarJugador.setVisible(true);
            }
        });
        bajaJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBajaJugador ventanaBajaJugador = new VentanaBajaJugador(vc);
                ventanaBajaJugador.setVisible(true);
            }
        });

        altaEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAltaEquipo ventanaAltaEquipo = new VentanaAltaEquipo(vc);
                ventanaAltaEquipo.setVisible(true);
            }
        });
        modificarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaModificarEquipo ventanaModificarEquipo = new VentanaModificarEquipo(vc);
                ventanaModificarEquipo.setVisible(true);
            }
        });
        modificarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBajaEquipo ventanaBajaEquipo = new VentanaBajaEquipo(vc);
                ventanaBajaEquipo.setVisible(true);
            }
        });
    }
}
