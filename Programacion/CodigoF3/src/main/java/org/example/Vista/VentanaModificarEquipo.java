package org.example.Vista;

import org.example.Controladores.JornadaController;
import org.example.Controladores.VistaController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase VentanaModificarEquipo.
 * Representa una ventana de diálogo para modificar los datos de un equipo.
 * Contiene campos de texto y un combo box para editar información.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class VentanaModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JTextField eNombre;
    private JTextField eFecha;
    private JComboBox cNombre;
    private VistaController vc;
    private String nombre;
    private JFrame ventanaAdministrador;

    public VentanaModificarEquipo(VistaController vc, String aNombre, JFrame ventanaAdministrador) {
        try {
            this.vc = vc;
            this.nombre = aNombre;
            this.ventanaAdministrador = ventanaAdministrador;
            setContentPane(contentPane);
            setModal(true);
            getRootPane().setDefaultButton(buttonOK);
            setSize(500, 580);
            setLocationRelativeTo(null);
            setResizable(false);

            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
            setIconImage(icon.getImage());

            eNombre.setEnabled(false);
            eFecha.setEnabled(false);
            buttonOK.setEnabled(false);
            vc.llenarComboBoxE(cNombre);

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

            cNombre.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedItem = (String) cNombre.getSelectedItem();
                        if (!selectedItem.equals("Selecciona un equipo...")) {
                            eNombre.setEnabled(true);
                        }
                    }
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        String selectedItem = (String) cNombre.getSelectedItem();
                        if (selectedItem.equals("Selecciona un equipo...")) {
                            eNombre.setEnabled(false);
                        }
                    }
                }
            });

            eNombre.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    Component opposite = e.getOppositeComponent();
                    if ((opposite instanceof JRadioButton) || opposite == cNombre) return;

                    if (eNombre.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaModificarEquipo.this, "El campo nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                        eNombre.requestFocus();
                    } else if (!validarNombre()) {
                        JOptionPane.showMessageDialog(VentanaModificarEquipo.this, "El nombre no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                        eNombre.requestFocus();
                    }
                    eNombre.setBorder(new LineBorder(Color.black, 1));
                }
            });

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

            eFecha.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    Component opposite = e.getOppositeComponent();
                    if ((opposite instanceof JRadioButton) || opposite == cNombre || opposite == eNombre) return;

                    if (eFecha.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaModificarEquipo.this, "El campo fecha no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                        eFecha.requestFocus();
                    } else if (!validarFecha()) {
                        JOptionPane.showMessageDialog(VentanaModificarEquipo.this, "La fecha no es válida. Formato esperado: dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                        eFecha.requestFocus();
                    }
                    eFecha.setBorder(new LineBorder(Color.black, 1));
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

            bLogo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    vc.mostrarVentanaInicio();
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onOK() {
        try {
            vc.modificarEquipo(eNombre.getText(), convertirFecha(eFecha.getText()), cNombre.getSelectedItem().toString());
            ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
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
