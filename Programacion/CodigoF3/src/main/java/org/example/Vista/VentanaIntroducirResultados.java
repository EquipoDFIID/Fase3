package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaIntroducirResultados extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JRadioButton equipo1RadioButton;
    private JRadioButton equipo2RadioButton;
    private JButton ACEPTARButton;
    private JPanel pPrincipal;
    private static VistaController vc;

    public VentanaIntroducirResultados(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setSize(500, 550);
        setLocationRelativeTo(null);
    }
}
