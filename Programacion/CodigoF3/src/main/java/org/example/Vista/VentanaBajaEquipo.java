package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Equipo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la ventana de baja de equipo.
 * Esta ventana permite al usuario seleccionar un equipo y proceder con su baja.
 */
public class VentanaBajaEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JComboBox cNombre;
    private static VistaController vc;

    public VentanaBajaEquipo(VistaController vc) {
        this.vc = vc;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        vc.llenarComboBox(cNombre);
        buttonOK.setEnabled(false); // Asegurarse de que esté desactivado inicialmente

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
                    buttonOK.setEnabled(selectedItem != null && !selectedItem.equals("Selecciona un equipo..."));
                }
            }
        });
    }

    private void onOK() {
        // Aquí puedes agregar lógica de confirmación o baja del equipo
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VentanaBajaEquipo dialog = new VentanaBajaEquipo(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    /*public void llenarComboBox() {
        ArrayList<Equipo> listaEquipos = vc.selectObjetoEquipo();
        cNombre.removeAllItems();

        // Opción por defecto no válida
        cNombre.addItem("Selecciona un equipo...");

        for (Equipo equipo : listaEquipos) {
            cNombre.addItem(equipo.getNombre());
        }

        // Selecciona por defecto la opción inicial (índice 0)
        cNombre.setSelectedIndex(0);
    }*/
}
