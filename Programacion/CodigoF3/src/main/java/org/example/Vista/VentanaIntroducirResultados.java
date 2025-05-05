package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VentanaIntroducirResultados extends JFrame {
    private JPanel panel1;
    private JButton bLogo;
    private JRadioButton bEquipo1;
    private JRadioButton bEquipo2;
    private JButton bAceptar;
    private JPanel pPrincipal;
    private JButton bSalir;
    private VistaController vc;
    private String nombre;

    public VentanaIntroducirResultados(VistaController vc, String aNombre) {
        this.vc = vc;
        this.nombre = aNombre;
        setContentPane(pPrincipal);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        bAceptar.setEnabled(false);


        bEquipo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bAceptar.setEnabled(true);
                } else {
                    bAceptar.setEnabled(false);
                }
            }
        });

        bEquipo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bAceptar.setEnabled(true);
                } else {
                    bAceptar.setEnabled(false);
                }
            }
        });

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
