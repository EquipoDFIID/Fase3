package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaInicio extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton usuarioButton;
    private JButton administradorButton;

    public VentanaInicio(VistaController vistaController) {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaInicio");
        setSize(500,500);
        setLocationRelativeTo(null);
    }
}
