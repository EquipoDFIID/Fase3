package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEquipos extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private static VistaController vc;

    public VentanaEquipos(VistaController vc, String nombre) {
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
                VentanaUsuario ventanaUsuario = new VentanaUsuario(vc, nombre);
                ventanaUsuario.setVisible(true);
                dispose();
            }
        });
        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio(vc);
                ventanaInicio.setVisible(true);
                dispose();
            }
        });
    }
}
