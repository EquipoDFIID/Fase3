package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Jugador;
import org.example.Modelo.Usuario;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
    private JPanel buttons;
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
            buttonImagen.setHorizontalAlignment(SwingConstants.LEFT);
        });

        usuarioRadioButton.addActionListener(e -> {
            relleno.setVisible(false);
            cambiandoVista = true;
            jUsuario.setVisible(true);
            jAdmin.setVisible(false);
            cambiandoVista = false;
            aNombre.setText("");
            aClave.setText("");
            buttonImagen.setHorizontalAlignment(SwingConstants.LEFT);
        });

        aNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (cambiandoVista) return;

                    if (aNombre.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                        aNombre.requestFocus();
                    } else {
                        Usuario a = vc.selectNombre(aNombre.getText());
                        Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                        Matcher m = p.matcher(aNombre.getText());
                        if (!m.matches()) {
                            JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                            aNombre.requestFocus();
                        } else {
                             {
                                if (a == null) {
                                    throw new DatoNoValido("No existe un administrador con ese nombre");
                                } else  if (!a.getTipo_usuario().equals("admin")) {
                                    throw new DatoNoValido("El nombre no corresponde a un admin");
                                }
                            }
                        }
                    }
                } catch (DatoNoValido ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        aClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cambiandoVista) return;

                String nombre = aNombre.getText().trim();
                String claveTexto = aClave.getText().trim();

                // Validar nombre (por si se ha escrito después de perder foco)
                if (!validarNombre(nombre)) {
                    aIniciarSesionButton.setEnabled(false);
                    return;
                }

                // Validar clave vacía
                if (claveTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
                    aIniciarSesionButton.setEnabled(false);
                    aClave.requestFocus();
                    return;
                }

                int clave;
                try {
                    clave = Integer.parseInt(claveTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La clave debe ser un número");
                    aClave.setText("");
                    aIniciarSesionButton.setEnabled(false);
                    aClave.requestFocus();
                    return;
                }

                if (!validarClave(String.valueOf(clave))) {
                    aClave.setText("");
                    JOptionPane.showMessageDialog(null, "La clave no tiene el formato correcto");
                    aIniciarSesionButton.setEnabled(false);
                    aClave.requestFocus();
                    return;
                }

                // Buscar usuario y comparar clave
                Usuario u = vc.selectNombre(nombre);
                if (u != null && Integer.parseInt(u.getClave()) == clave) {
                    aIniciarSesionButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre o clave incorrectos");
                    aIniciarSesionButton.setEnabled(false);
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
            dispose();
        });

        aIniciarSesionButton.addActionListener(e -> {
            VentanaAdministrador ventanaAdministrador = new VentanaAdministrador(vc, aNombre.getText());
            ventanaAdministrador.setVisible(true);
            dispose();
        });

        uIniciarSesionButton.addActionListener(e -> {
            VentanaUsuario ventanaUsuario = new VentanaUsuario(vc, uNombre.getText());
            ventanaUsuario.setVisible(true);
            dispose();
        });
        uNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if (!uNombre.getText().isEmpty()) {
                    crearCuentaButton.setEnabled(false);
                } else {
                    crearCuentaButton.setEnabled(true);
                }
            }
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
