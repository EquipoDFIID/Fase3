package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;

public class VentanaBajaJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JComboBox cJugador;
    private static VistaController vc;
    private static String nombre;
    private JFrame ventanaAdministrador;

    /**
     * Clase que representa la ventana de baja de jugador.
     * Esta ventana permite al usuario seleccionar un jugador y proceder con su baja.
     */

    public VentanaBajaJugador(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        try {
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

            vc.llenarComboBoxJ(cJugador);
            buttonOK.setEnabled(false);

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
                        getRootPane().setDefaultButton(buttonOK); // Agregado
                        buttonOK.setEnabled(selectedItem != null && !selectedItem.equals("Selecciona un jugador..."));
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
            vc.bajaJugador(cJugador.getSelectedItem().toString());
            ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
            dispose(); //
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
        dispose(); //
    }
}
