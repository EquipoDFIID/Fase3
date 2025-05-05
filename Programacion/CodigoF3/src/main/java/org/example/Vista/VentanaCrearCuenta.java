package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la ventana para crear una cuenta de usuario.
 * Permite al usuario ingresar una clave y confirmarla antes de proceder.
 */
public class VentanaCrearCuenta extends JFrame {
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JTextField cClave;
    private JTextField ccClave;
    private JPanel pPrincipal;
    private JTextField cNombre;
    private static VistaController vc;

    public VentanaCrearCuenta(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaCrearCuenta");
        setSize(500,580);
        setLocationRelativeTo(null);
        buttonOK.setEnabled(false);
        setResizable(false);

        cClave.setEnabled(false);
        ccClave.setEnabled(false);

        cNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNombre()) {
                    cClave.setEnabled(true);
                    cNombre.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    cClave.setEnabled(false);
                    cNombre.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        cNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    cNombre.requestFocus();
                } else if (!validarNombre()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    cNombre.requestFocus();
                }
                cNombre.setBorder(new LineBorder(Color.black, 1));
            }
        });

        cClave.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String clave1 = cClave.getText();
                String clave2 = ccClave.getText();

                if (!validarClave()) {
                    cClave.setBorder(new LineBorder(Color.RED, 1));
                    ccClave.setEnabled(false);
                    buttonOK.setEnabled(false);
                    return;
                } else {
                    cClave.setBorder(new LineBorder(Color.GREEN, 1));
                    ccClave.setEnabled(true);
                }
            }
        });

        cClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cClave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    cClave.requestFocus();
                } else if (!validarClave()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    cClave.requestFocus();
                }
                cClave.setBorder(new LineBorder(Color.black, 1));
            }
        });

        ccClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String clave2 = ccClave.getText();
                String clave1 = cClave.getText();
                // Si ya hay algo en ccClave, validar coincidencia
                if (!clave2.isEmpty()) {
                    if (clave1.equals(clave2)) {
                        cClave.setBorder(new LineBorder(Color.GREEN, 1));
                        ccClave.setBorder(new LineBorder(Color.GREEN, 1));
                        buttonOK.setEnabled(true);
                    } else {
                        cClave.setBorder(new LineBorder(Color.RED, 1));
                        ccClave.setBorder(new LineBorder(Color.RED, 1));
                        buttonOK.setEnabled(false);
                    }
                }
            }
        });

        ccClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (ccClave.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    ccClave.requestFocus();
                } else if (!validarClave()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    ccClave.requestFocus();
                }
                ccClave.setBorder(new LineBorder(Color.black, 1));
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio(vc);
                ventanaInicio.setVisible(true);
                dispose();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.crearCuenta(cNombre.getText(), cClave.getText());
                JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "Usuario creado exitosamente", "Exitosamente", JOptionPane.INFORMATION_MESSAGE);
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

    private boolean validarNombre() {
        String nombre = cNombre.getText();
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
    }

    private boolean validarClave() {
        String clave = cClave.getText();
        return clave.matches("^[0-9]{4}$");
    }
}
