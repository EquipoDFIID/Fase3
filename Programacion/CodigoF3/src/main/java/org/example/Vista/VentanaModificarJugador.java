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
    private static VistaController vc;
    private static VentanaAdministrador ventana;

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
        llenarComboBox();

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
                    }
                }
            }
        });



    }
    public void llenarComboBox(){
        ArrayList<Jugador> listaJugadores=vc.selectNicknameJugador();
        cJugador.removeAllItems();

        cJugador.addItem("Selecciona un jugador...");

        for (Jugador jugador : listaJugadores) {
            cJugador.addItem(jugador.getNombre());
        }

        cJugador.setSelectedIndex(0);
    }

    private void onOK() {
        // Lógica para modificar al jugador seleccionado
        // Podés acceder al comboBox con comboBox1.getSelectedItem()
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
}
