package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Jugador;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase VentanaModificarJugador.
 * Ventana de diálogo para modificar los datos de un jugador existente.
 * Permite seleccionar un jugador desde un comboBox y editar sus datos.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class VentanaModificarJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField jNickname;
    private JTextField jSueldo;
    private JComboBox cJugador;
    private JPanel pPrincipal;
    private JTextField jNombre;
    private JTextField jApellido;
    private JTextField jNacionalidad;
    private JTextField jFecha;
    private JComboBox jEquipo;
    private static VistaController vc;

    public VentanaModificarJugador(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        jNombre.setEnabled(false);
        jApellido.setEnabled(false);
        jNacionalidad.setEnabled(false);
        jFecha.setEnabled(false);
        jNickname.setEnabled(false);
        jSueldo.setEnabled(false);
        jEquipo.setEnabled(false);
        vc.llenarComboBoxJ(cJugador);
        vc.llenarComboBoxE(jEquipo);

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

        cJugador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) cJugador.getSelectedItem();
                    if (!selectedItem.equals("Selecciona un jugador...")) {
                        jNombre.setEnabled(true);
                        jApellido.setEnabled(true);
                        jNacionalidad.setEnabled(true);
                        jFecha.setEnabled(true);
                        jNickname.setEnabled(true);
                        jSueldo.setEnabled(true);
                        jEquipo.setEnabled(true);
                    }
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    String selectedItem = (String) cJugador.getSelectedItem();
                    if (selectedItem.equals("Selecciona un jugador...")) {
                        jNombre.setEnabled(false);
                        jApellido.setEnabled(false);
                        jNacionalidad.setEnabled(false);
                        jFecha.setEnabled(false);
                        jNickname.setEnabled(false);
                        jSueldo.setEnabled(false);
                        jEquipo.setEnabled(false);
                    }
                }
            }
        });



    }
    /*public void llenarComboBox(){
        ArrayList<Jugador> listaJugadores=vc.();
        cJugador.removeAllItems();

        cJugador.addItem("Selecciona un jugador...");

        for (Jugador jugador : listaJugadores) {
            cJugador.addItem(jugador.getNombre());
        }

        cJugador.setSelectedIndex(0);
    }*/

    private void onOK() {
        Jugador jugador = new Jugador();
        jugador.setNombre(jNombre.getText());
        jugador.setApellido(jApellido.getText());
        jugador.setNacionalidad(jNacionalidad.getText());
        jugador.setFechaNacimiento(convertirFecha(jFecha.getText()));
        jugador.setNickname(jNickname.getText());
        jugador.setSueldo(Double.parseDouble(jSueldo.getText()));
        jugador.setEquipo(vc.buscarComboBoxE(jEquipo));
        vc.modificarJugador(jugador, cJugador.getSelectedItem().toString());
        dispose();
    }

    private void onCancel() {
        // Código adicional si hace falta
        dispose();
    }

    public static void main(String[] args) {
        VentanaModificarJugador dialog = new VentanaModificarJugador(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
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
