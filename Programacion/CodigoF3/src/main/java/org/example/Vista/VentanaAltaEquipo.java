package org.example.Vista;

import org.example.Controladores.VistaController;

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

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        inicializarCampos();

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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        agregarListeners();
        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });
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

    private void onOK() {
        vc.altaEquipo(eNombre.getText(), eFecha.getText());
        dispose();
    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
        dispose(); // Cierra la ventana actual
    }
}
