package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VentanaAltaEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JTextField eID;
    private JTextField eNombre;
    private JTextField eFecha;
    private static VistaController vc;
    private static JFrame ventanaAdministrador;
    private static String nombre;

    /**
     * Clase que representa la ventana de alta de equipo.
     * Esta ventana permite al usuario introducir los datos necesarios
     * para registrar un nuevo equipo en la aplicación.
     */
    public VentanaAltaEquipo(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        this.vc = vc;
        this.nombre = aNombre;
        this.ventanaAdministrador = ventanaAdmin;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        iconoVentana();

        inicializarCampos();
        agregarListeners();

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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    private void inicializarCampos() {
        buttonOK.setEnabled(false);
        eFecha.setEnabled(false);
    }

    private void agregarListeners() {
        eNombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarNombre()) {
                    eFecha.setEnabled(true);
                    eNombre.setBorder(new LineBorder(Color.GREEN, 1));

                } else {
                    eFecha.setEnabled(false);
                    eNombre.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        eFecha.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (validarFecha()) {
                    buttonOK.setEnabled(true);
                    getRootPane().setDefaultButton(buttonOK); // Agregado
                    eFecha.setBorder(new LineBorder(Color.GREEN, 1));
                } else {
                    buttonOK.setEnabled(false);
                    eFecha.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });

        eNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (eNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaEquipo.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    eNombre.requestFocus();
                } else if (!validarNombre()) {
                    JOptionPane.showMessageDialog(VentanaAltaEquipo.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                    eNombre.requestFocus();
                }
                eNombre.setBorder(new LineBorder(Color.BLACK, 1));
            }
        });

        eFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Component opposite = e.getOppositeComponent();
                if ((opposite instanceof JRadioButton) || opposite == eNombre) return;

                if (eFecha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaAltaEquipo.this, "El campo fecha no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    eFecha.requestFocus();
                } else if (!validarFecha()) {
                    JOptionPane.showMessageDialog(VentanaAltaEquipo.this, "La fecha no es válida. Formato esperado: dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    eFecha.requestFocus();
                }
                eFecha.setBorder(new LineBorder(Color.BLACK, 1));
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

    private void onOK() {
        try {
            boolean insertado;
            insertado = vc.altaEquipo(eNombre.getText(), convertirFecha(eFecha.getText()));

            if (insertado) {
                JOptionPane.showMessageDialog(VentanaAltaEquipo.this, "Equipo creado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new DatoNoValido("Error al insertar el Equipo");
            }

            ventanaAdministrador.setVisible(true);
            dispose();
        } catch (DatoNoValido error) {
            JOptionPane.showMessageDialog(VentanaAltaEquipo.this, error.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VentanaAltaEquipo.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean validarNombre() {
        String nombre = eNombre.getText();
        return nombre.matches("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+$");
    }

    private boolean validarFecha() {
        String fechaTexto = eFecha.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(fechaTexto, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
