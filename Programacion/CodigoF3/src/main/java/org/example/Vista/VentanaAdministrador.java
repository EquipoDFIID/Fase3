package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

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
    }
}
