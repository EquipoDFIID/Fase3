package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Equipo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase VentanaModificarJugador.
 * Ventana de diálogo para modificar los datos de un jugador existente.
 * Permite seleccionar un jugador desde un comboBox y editar sus datos.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VentanaModificarJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JTextField jNickname;
    private JTextField jSueldo;
    private JComboBox cJugador;
    private JPanel pPrincipal;
    private JTextField jNombre;
    private JTextField jApellido;
    private JTextField jNacionalidad;
    private JTextField jFecha;
    private JComboBox jEquipo;
    private VistaController vc;
    private Equipo ej;
    private String nombre;
    private JFrame ventanaAdministrador;

    public VentanaModificarJugador(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        try {
            this.vc = vc;
            this.nombre = aNombre;
            this.ventanaAdministrador = ventanaAdmin;
            setContentPane(pPrincipal);
            setModal(true);
            getRootPane().setDefaultButton(buttonOK);
            setSize(500, 580);
            setLocationRelativeTo(null);
            setResizable(false);
            iconoVentana();

            inicializarCampos();
            agregarListeners();

            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() throws Exception {
        jNombre.setEnabled(false);
        jApellido.setEnabled(false);
        jNacionalidad.setEnabled(false);
        jFecha.setEnabled(false);
        jNickname.setEnabled(false);
        jSueldo.setEnabled(false);
        jEquipo.setEnabled(false);
        vc.llenarComboBoxJ(cJugador);
        vc.llenarComboBoxE(jEquipo);
    }

    public void agregarListeners() {
        cJugador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) cJugador.getSelectedItem();
                    if (!selectedItem.equals("Selecciona un jugador...")) {
                        jNombre.setEnabled(true);

                    }
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    String selectedItem = (String) cJugador.getSelectedItem();
                    if (selectedItem.equals("Selecciona un jugador...")) {
                        jNombre.setEnabled(false);
                    }
                }
            }
        });

        jNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNombre()) {
                    jApellido.setEnabled(true);
                    jNombre.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    jApellido.setEnabled(false);
                    jNombre.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        jApellido.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarApellido()) {
                    jNacionalidad.setEnabled(true);
                    jApellido.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    jNacionalidad.setEnabled(false);
                    jApellido.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        jNacionalidad.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNacionalidad()) {
                    jFecha.setEnabled(true);
                    jNacionalidad.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    jFecha.setEnabled(false);
                    jNacionalidad.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        jFecha.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarFecha()) {
                    jNickname.setEnabled(true);
                    jFecha.setBorder(new LineBorder(Color.GREEN, 1));

                } else {
                    jNickname.setEnabled(false);
                    jFecha.setBorder(new LineBorder(Color.RED, 1));

                }
            }
        });

        jNickname.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNickname()) {
                    jSueldo.setEnabled(true);
                    jNickname.setBorder(new LineBorder(Color.GREEN, 1));

                } else {
                    jSueldo.setEnabled(false);
                    jNickname.setBorder(new LineBorder(Color.RED, 1));

                }
            }
        });

        jSueldo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarSueldo()) {
                    jEquipo.setEnabled(true);
                    jSueldo.setBorder(new LineBorder(Color.GREEN, 1));

                } else {
                    jEquipo.setEnabled(false);
                    jSueldo.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        jEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jEquipo.isEnabled() && jEquipo.getSelectedIndex() > 0) {
                    buttonOK.setEnabled(true);
                    getRootPane().setDefaultButton(buttonOK); // Agregado
                    jEquipo.setBorder(new LineBorder(Color.black, 1));
                } else {
                    buttonOK.setEnabled(false);
                    jEquipo.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        jNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == cJugador) return;

                if (jNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNombre.requestFocus();
                } else if (!validarNombre()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    jNombre.requestFocus();
                }
                jNombre.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == cJugador) return;

                if (jApellido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo apellido no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jApellido.requestFocus();
                } else if (!validarApellido()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El apellido no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    jApellido.requestFocus();
                }
                jApellido.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jNacionalidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == jApellido || opposite == cJugador) return;

                if (jNacionalidad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo nacionalidad no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNacionalidad.requestFocus();
                } else if (!validarNacionalidad()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "La nacionalidad no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                    jNacionalidad.requestFocus();
                }
                jNacionalidad.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == jApellido || opposite == jNacionalidad || opposite == cJugador) return;

                if (jFecha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo fecha no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jFecha.requestFocus();
                } else if (!validarFecha()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "La fecha no es válida. Formato esperado: dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    jFecha.requestFocus();
                }
                jFecha.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == jApellido || opposite == jNacionalidad || opposite == jFecha || opposite == cJugador) return;

                if (jNickname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo nickname no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jNickname.requestFocus();
                } else if (!validarNickname()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El nickname debe tener entre 3 y 15 caracteres alfanuméricos o guiones bajos", "Error", JOptionPane.ERROR_MESSAGE);
                    jNickname.requestFocus();
                }
                jNickname.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == jApellido || opposite == jNacionalidad || opposite == jFecha || opposite == jNickname || opposite == cJugador) return;

                if (jSueldo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El campo sueldo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jSueldo.requestFocus();
                } else if (!validarSueldo()) {
                    JOptionPane.showMessageDialog(VentanaModificarJugador.this, "El sueldo debe ser un número válido (puede tener hasta 2 decimales)", "Error", JOptionPane.ERROR_MESSAGE);
                    jSueldo.requestFocus();
                }
                jSueldo.setBorder(new LineBorder(Color.black, 1));
            }
        });

        jEquipo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == jNombre || opposite == jApellido || opposite == jNacionalidad || opposite == jFecha || opposite == jNickname || opposite == jSueldo|| opposite == cJugador) return;
            }
        });

        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

    private void onOK() {
        try {
            boolean modificado;

            modificado = vc.modificarJugador(
                    jNombre.getText(),
                    jApellido.getText(),
                    jNacionalidad.getText(),
                    convertirFecha(jFecha.getText()),
                    jNickname.getText(),
                    Double.parseDouble(jSueldo.getText()),
                    ej = vc.buscarComboBoxE(jEquipo),
                    cJugador.getSelectedItem().toString()
            );

            if (modificado) {
                JOptionPane.showMessageDialog(VentanaModificarJugador.this, "Jugador modificado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new DatoNoValido("Error al modificar el Jugador");
            }

            ventanaAdministrador.setVisible(true);
            dispose();
        } catch (DatoNoValido error) {
            JOptionPane.showMessageDialog(VentanaModificarJugador.this, error.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VentanaModificarJugador.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true);
        dispose();
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
}
