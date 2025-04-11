package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaInformes extends JFrame {
    private JButton button1;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private static VistaController vc;

    public VentanaInformes(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
