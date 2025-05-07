package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase que representa la ventana de baja de equipo.
 * Esta ventana permite al usuario seleccionar un equipo y proceder con su baja.
 */
public class VentanaBajaEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JComboBox cNombre;
    private static VistaController vc;
    private static String nombre;
    private JFrame ventanaAdministrador;

    public VentanaBajaEquipo(VistaController vc, String aNombre, JFrame ventanaAdmin) {
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

        vc.llenarComboBoxE(cNombre);
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
        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });
    }

    private void onOK() {
        vc.bajaEquipo(cNombre.getSelectedItem().toString());
        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
        dispose(); //
    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
        dispose(); //
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
