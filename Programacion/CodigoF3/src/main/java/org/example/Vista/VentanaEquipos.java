package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEquipos extends JFrame {
    private JButton button1;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private static VistaController vc;

    public VentanaEquipos(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,580);
        setLocationRelativeTo(null);
        setResizable(false);

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
