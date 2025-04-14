package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Equipo;
import org.example.Modelo.Jugador;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaAltaJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField jID;
    private JTextField jNickname;
    private JTextField jSueldo;
    private JTextField jNombre;
    private JTextField jApellido;
    private JTextField jNacionalidad;
    private JTextField jFecha;
    private JComboBox jEquipo;
    private static VistaController vc;

    private static VentanaAdministrador ventana;

    public VentanaAltaJugador(VistaController vc) {
        this.vc = vc;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        vc.llenarComboBox(jEquipo);
        inicializarCampos();

        // Acciones de botones
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        agregarListeners();
    }

    private void inicializarCampos() {
        jID.setEnabled(true);
        jNombre.setEnabled(false);
        jApellido.setEnabled(false);
        jNacionalidad.setEnabled(false);
        jFecha.setEnabled(false);
        jNickname.setEnabled(false);
        jSueldo.setEnabled(false);
        jEquipo.setEnabled(false);
        buttonOK.setEnabled(false);
    }

    private void agregarListeners() {
        jID.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarID()) {
                    jNombre.setEnabled(true);
                } else {
                    jNombre.setEnabled(false);                }
            }
        });

        jNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNombre()) {
                    jApellido.setEnabled(true);
                } else {
                    jApellido.setEnabled(false);
                }
            }
        });

        jApellido.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarApellido()) {
                    jNacionalidad.setEnabled(true);
                } else {
                    jNacionalidad.setEnabled(false);
                }
            }
        });

        jNacionalidad.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNacionalidad()) {
                    jFecha.setEnabled(true);
                } else {
                    jFecha.setEnabled(false);
                }
            }
        });

        jFecha.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarFecha()) {
                    jNickname.setEnabled(true);
                } else {
                    jNickname.setEnabled(false);
                }
            }
        });

        jNickname.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNickname()) {
                    jSueldo.setEnabled(true);
                } else {
                    jSueldo.setEnabled(false);
                }
            }
        });

        jSueldo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarSueldo()) {
                    jEquipo.setEnabled(true);
                } else {
                    jEquipo.setEnabled(false);
                }
            }
        });

        jEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jEquipo.isEnabled() && jEquipo.getSelectedIndex() > 0) {
                    buttonOK.setEnabled(true);
                } else {
                    buttonOK.setEnabled(false);
                }
            }
        });

        jID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jID.requestFocus();
                } else if (!validarID()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El ID debe tener exactamente 4 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                    jID.requestFocus();
                }
            }
        });

        jNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNombre.requestFocus();
                } else if (!validarNombre()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    jNombre.requestFocus();
                }
            }
        });

        jApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jApellido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo apellido no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jApellido.requestFocus();
                } else if (!validarApellido()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El apellido no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    jApellido.requestFocus();
                }
            }
        });

        jNacionalidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jNacionalidad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo nacionalidad no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNacionalidad.requestFocus();
                } else if (!validarNacionalidad()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "La nacionalidad no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                    jNacionalidad.requestFocus();
                }
            }
        });

        jFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jFecha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo fecha no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jFecha.requestFocus();
                } else if (!validarFecha()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "La fecha no es válida. Formato esperado: dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    jFecha.requestFocus();
                }
            }
        });

        jNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jNickname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo nickname no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNickname.requestFocus();
                } else if (!validarNickname()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El nickname debe tener entre 3 y 15 caracteres alfanuméricos o guiones bajos", "Error", JOptionPane.ERROR_MESSAGE);
                    jNickname.requestFocus();
                }
            }
        });

        jSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (jSueldo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El campo sueldo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jSueldo.requestFocus();
                } else if (!validarSueldo()) {
                    JOptionPane.showMessageDialog(VentanaAltaJugador.this, "El sueldo debe ser un número válido (puede tener hasta 2 decimales)", "Error", JOptionPane.ERROR_MESSAGE);
                    jSueldo.requestFocus();
                }
            }
        });
    }

    private boolean validarID() {
        String id = jID.getText();
        return id.matches("^[0-9]{4}$");
    }

    private boolean validarNombre() {
        String nombre = jNombre.getText();
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
    }

    private boolean validarApellido() {
        String apellido = jApellido.getText();
        return apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
    }

    private boolean validarNacionalidad() {
        String nacionalidad = jNacionalidad.getText();
        return nacionalidad.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
    }

    private boolean validarFecha() {
        String fechaTexto = jFecha.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(fechaTexto, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validarNickname() {
        String nick = jNickname.getText();
        return nick.matches("^[a-zA-Z0-9_]{3,15}$");
    }

    private boolean validarSueldo() {
        String sueldo = jSueldo.getText();
        return sueldo.matches("^[0-9]+(\\.[0-9]{1,2})?$");
    }

    private LocalDate convertirFecha(String fechaTexto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(fechaTexto, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha no tiene el formato válido (dd/mm/aaaa) o es inválida.");
            return null;
        }
    }

    private void onOK() {
        Jugador j = new Jugador();
        j.setIdJugador(Integer.parseInt(jID.getText()));
        j.setNombre(jNombre.getText());
        j.setApellido(jApellido.getText());
        j.setNacionalidad(jNacionalidad.getText());
        j.setFechaNacimiento(convertirFecha(jFecha.getText()));
        j.setNickname(jNickname.getText());
        j.setSueldo(Double.parseDouble(jSueldo.getText()));
        j.setEquipo(vc.buscarComboBox(jEquipo));
        vc.altaJugador(j);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        VentanaAltaJugador dialog = new VentanaAltaJugador(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
