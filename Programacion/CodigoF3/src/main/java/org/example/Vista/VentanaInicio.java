package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.foreign.MemoryLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaInicio extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton crearCuentaButton;
    private JPanel jAdmin;
    private JPanel jUsuario;
    private JRadioButton administradorRadioButton;
    private JRadioButton usuarioRadioButton;
    private JTextField aNombre;
    private JTextField aClave;
    private JTextField uNombre;
    private JTextField uClave;


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

                if (aNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(aNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                    }

                }

                if (aClave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacio");
                } else {
                    Pattern p = Pattern.compile("^[0-9]{4}$");
                    Matcher m = p.matcher(aClave.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                    }
                }
            }
        });

        usuarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jUsuario.setVisible(true);

                if (uNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(uNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                    }

                }

                if (uClave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacio");
                } else {
                    Pattern p = Pattern.compile("^[0-9]{4}$");
                    Matcher m = p.matcher(uClave.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                    }
                }
            }
        });
    }
}
