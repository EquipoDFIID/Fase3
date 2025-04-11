package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaUsuario extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton resultadosDeÚltimaJornadaButton;
    private VistaController vc;

    public VentanaUsuario(VistaController vc) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,550);
        setLocationRelativeTo(null);
    }
}
