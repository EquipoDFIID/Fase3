package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    private JButton uIniciarSesionButton;
    private JButton aIniciarSesionButton;
    private static VistaController vc;

    public VentanaInicio(VistaController vc) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaInicio");
        setSize(500, 550);
        setLocationRelativeTo(null);

        jAdmin.setVisible(false);
        jUsuario.setVisible(false);
        aIniciarSesionButton.setEnabled(false);
        uIniciarSesionButton.setEnabled(false);

        administradorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jAdmin.setVisible(true);
                jUsuario.setVisible(false);
            }
        });

        aNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (aNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                    aNombre.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(aNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                        aNombre.requestFocus();
                    }
                }
            }
        });

        aClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                boolean nombreValido = validarNombre(aNombre.getText());
                if (!nombreValido) {
                    aIniciarSesionButton.setEnabled(false);
                    return;
                }

                String clave = aClave.getText();
                if (clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
                    aIniciarSesionButton.setEnabled(false);
                } else {
                    if (validarClave(clave)) {
                        aIniciarSesionButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                        aIniciarSesionButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                aIniciarSesionButton.setEnabled(false);
            }
        });

        usuarioRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jUsuario.setVisible(true);
                jAdmin.setVisible(false);
            }
        });

        uNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (uNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                    uNombre.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(uNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                        uNombre.requestFocus();
                    }
                }
            }
        });

        uClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                boolean nombreValido = validarNombre(uNombre.getText());
                if (!nombreValido) {
                    uIniciarSesionButton.setEnabled(false);
                    return;
                }

                String clave = uClave.getText();
                if (clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
                    uIniciarSesionButton.setEnabled(false);
                } else {
                    if (validarClave(clave)) {
                        uIniciarSesionButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                        uIniciarSesionButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                uIniciarSesionButton.setEnabled(false);
            }
        });

        crearCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCrearCuenta ventanaCuenta = new VentanaCrearCuenta(vc);
                ventanaCuenta.setVisible(true);
            }
        });

        aIniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAdministrador ventanaAdministrador = new VentanaAdministrador(vc);
                ventanaAdministrador.setVisible(true);
            }
        });

        uIniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaUsuario ventanaUsuario = new VentanaUsuario(vc);
                ventanaUsuario.setVisible(true);
            }
        });
    }

    private boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) return false;
        Pattern pattern = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
        return pattern.matcher(nombre).matches();
    }

    private boolean validarClave(String clave) {
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        return pattern.matcher(clave).matches();
    }
}
