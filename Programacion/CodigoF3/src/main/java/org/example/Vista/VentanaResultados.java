package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaResultados extends JFrame {
    private JButton button1;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private static VistaController vc;

    public VentanaResultados(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,550);
        setLocationRelativeTo(null);
    }
}
