package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Clase que representa la ventana de inicio de sesión del sistema.
 * Permite iniciar sesión como administrador o usuario, o crear una nueva cuenta.
 */
public class VentanaInicio extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton buttonImagen;
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
    private JLabel relleno;
    private static VistaController vc;

    private boolean cambiandoVista = false;

    public VentanaInicio(VistaController vc) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaInicio");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        jAdmin.setVisible(false);
        jUsuario.setVisible(false);
        aIniciarSesionButton.setEnabled(false);
        uIniciarSesionButton.setEnabled(false);

        administradorRadioButton.addActionListener(e -> {
            relleno.setVisible(false);
            cambiandoVista = true;
            jAdmin.setVisible(true);
            jUsuario.setVisible(false);
            cambiandoVista = false;
            uNombre.setText("");
            uClave.setText("");
            relleno.setVisible(false);
            //buttonImagen.setHorizontalAlignment(SwingConstants.LEFT);
        });

        usuarioRadioButton.addActionListener(e -> {
            cambiandoVista = true;
            jUsuario.setVisible(true);
            jAdmin.setVisible(false);
            cambiandoVista = false;
            aNombre.setText("");
            aClave.setText("");
            //buttonImagen.setHorizontalAlignment(SwingConstants.RIGHT);
        });

        aNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cambiandoVista) return;

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
                if (cambiandoVista) return;

                if (!validarNombre(aNombre.getText())) {
                    aIniciarSesionButton.setEnabled(false);
                    return;
                }

                String clave = aClave.getText();
                if (clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
                    aIniciarSesionButton.setEnabled(false);
                    aClave.requestFocus();
                } else if (!validarClave(clave)) {
                    aClave.setText("");
                    JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                    aIniciarSesionButton.setEnabled(false);
                    aClave.requestFocus();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                aIniciarSesionButton.setEnabled(false);
            }
        });

        aClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarNombre(aNombre.getText()) && validarClave(aClave.getText())) {
                    aIniciarSesionButton.setEnabled(true);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Ejecutar la acción como si se hubiera hecho clic
                        aIniciarSesionButton.doClick();
                    }
                } else {
                    aIniciarSesionButton.setEnabled(false);
                }
            }
        });

        uNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cambiandoVista) return;

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
                if (cambiandoVista) return;

                if (!validarNombre(uNombre.getText())) {
                    uIniciarSesionButton.setEnabled(false);
                    return;
                }

                String clave = uClave.getText();
                if (clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
                    uIniciarSesionButton.setEnabled(false);
                    uClave.requestFocus();
                } else if (!validarClave(clave)) {
                    uClave.setText("");
                    JOptionPane.showMessageDialog(null, "La clave no puede tener ese formato");
                    uIniciarSesionButton.setEnabled(false);
                    uClave.requestFocus();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                uIniciarSesionButton.setEnabled(false);
            }
        });

        uClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarNombre(uNombre.getText()) && validarClave(uClave.getText())) {
                    uIniciarSesionButton.setEnabled(true);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Ejecutar la acción como si se hubiera hecho clic
                        uIniciarSesionButton.doClick();
                    }
                } else {
                    uIniciarSesionButton.setEnabled(false);
                }
            }
        });

        crearCuentaButton.addActionListener(e -> {
            VentanaCrearCuenta ventanaCuenta = new VentanaCrearCuenta(vc);
            ventanaCuenta.setVisible(true);
            setVisible(false);
        });

        aIniciarSesionButton.addActionListener(e -> {
            VentanaAdministrador ventanaAdministrador = new VentanaAdministrador(vc);
            ventanaAdministrador.setVisible(true);
            setVisible(false);
        });

        uIniciarSesionButton.addActionListener(e -> {
            VentanaUsuario ventanaUsuario = new VentanaUsuario(vc);
            ventanaUsuario.setVisible(true);
            setVisible(false);
        });
    }
    /**
     * Valida que el nombre comience por mayúscula y solo tenga letras.
     * @param nombre Nombre a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) return false;
        Pattern pattern = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
        return pattern.matcher(nombre).matches();
    }
    /**
     * Valida que la clave tenga exactamente 4 dígitos numéricos.
     * @param clave Clave a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean validarClave(String clave) {
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        return pattern.matcher(clave).matches();
    }
}
