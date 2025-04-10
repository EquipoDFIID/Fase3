package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton crearCuentaButton;
    private JPanel jAdmin;
    private JPanel jUsuario;
    private JRadioButton administradorRadioButton;
    private JRadioButton usuarioRadioButton;

    public VentanaInicio(VistaController vistaController) {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaInicio");
        setSize(500,500);
        setLocationRelativeTo(null);

        jAdmin.setVisible(false);
        jUsuario.setVisible(false);


        administradorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jAdmin.setVisible(true);
            }
        });

        usuarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jUsuario.setVisible(true);
            }
        });
    }
}
