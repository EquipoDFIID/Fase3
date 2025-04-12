package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Equipo;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase VentanaModificarEquipo.
 * Representa una ventana de diálogo para modificar los datos de un equipo.
 * Contiene campos de texto y un combo box para editar información.
 */

import java.util.ArrayList;


public class VentanaModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField eNombre;
    private JTextField eFecha;
    private JComboBox cNombre;
    private static VistaController vc;
    private static VentanaAdministrador ventana;

    public VentanaModificarEquipo(VistaController vc, VentanaAdministrador ventana) {
        this.vc = vc;
        this.ventana = ventana;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        llenarComboBox();
        setResizable(false);

        eNombre.setEnabled(false);
        eFecha.setEnabled(false);

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
                        eFecha.setEnabled(true);
                    }
                }
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    String selectedItem = (String) cNombre.getSelectedItem();
                    if (selectedItem.equals("Selecciona un equipo...")) {
                        eNombre.setEnabled(false);
                        eFecha.setEnabled(false);
                    }
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
    }
    public void llenarComboBox(){
        ArrayList<Equipo> listaEquipos=vc.selectNombreEquipo();
        cNombre.removeAllItems();

        cNombre.addItem("Selecciona un equipo...");

        for (Equipo equipo : listaEquipos){
            cNombre.addItem(equipo.getNombre());
        }

        cNombre.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        VentanaModificarEquipo dialog = new VentanaModificarEquipo(vc, ventana);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
