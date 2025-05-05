package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInformes extends JFrame {
    private JButton bLogo;
    private JButton bSalir;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private VistaController vc;
    private String nombre;

    public VentanaInformes(VistaController vc, String aNombre) {
        this.vc = vc;
        this.nombre = aNombre;
        setContentPane(pPrincipal);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAdministrador ventanaAdministrador = new VentanaAdministrador(vc, nombre);
                ventanaAdministrador.setVisible(true);
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
    }
}
