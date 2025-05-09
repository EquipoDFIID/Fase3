package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;

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
    private JTextField cNickname;
    private VistaController vc;
    private JFrame ventanaInicio;


    public VentanaCrearCuenta(VistaController vc, JFrame ventanaIni) {
        this.vc = vc;
        this.ventanaInicio = ventanaIni;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaCrearCuenta");
        setSize(500,580);
        setLocationRelativeTo(null);
        buttonOK.setEnabled(false);
        setResizable(false);
        iconoVentana();

        inicializarCampos();
        agregarListeners();
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() {
        cNombre.setEnabled(false);
        cClave.setEnabled(false);
        ccClave.setEnabled(false);
    }

    public void agregarListeners() {
        cNickname.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNickname()) {
                    cNombre.setEnabled(true);
                    cNickname.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    cNombre.setEnabled(false);
                    cNickname.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        cNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (cNickname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El campo nickname no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    cNickname.requestFocus();
                } else if (!validarNickname()) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El nickname no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    cNickname.requestFocus();
                }
                cNickname.setBorder(new LineBorder(Color.black, 1));
            }
        });

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
                        getRootPane().setDefaultButton(buttonOK); // Agregado
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
                ventanaInicio.setVisible(true);
                dispose();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!vc.comprobarNickname(cNickname.getText())) {
                        boolean creada;
                        creada = vc.crearCuenta(cNickname.getText(), cNombre.getText(), cClave.getText());

                        if (creada) {
                            JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "Usuario creado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            throw new DatoNoValido("Error al crear el Usuario");
                        }

                        ventanaInicio.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(VentanaCrearCuenta.this, "El nickname ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                        resetCampos();
                    }
                } catch (DatoNoValido error) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, error.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaCrearCuenta.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    private boolean validarNickname() {
        String nick = cNickname.getText();
        return nick.matches("^[a-zA-Z0-9_]{3,15}$");
    }

    private boolean validarNombre() {
        String nombre = cNombre.getText();
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
    }

    private boolean validarClave() {
        String clave = cClave.getText();
        return clave.matches("^[0-9]{4}$");
    }

    public void resetCampos() {
        cNickname.setText("");
        cNombre.setText("");
        cClave.setText("");
        ccClave.setText("");
        cNickname.setBorder(new LineBorder(Color.black, 1));
        cNombre.setBorder(new LineBorder(Color.black, 1));
        cClave.setBorder(new LineBorder(Color.black, 1));
        ccClave.setBorder(new LineBorder(Color.black, 1));
        cNickname.requestFocus();
    }
}
