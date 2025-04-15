package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Jugador;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaBajaJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JComboBox cJugador;
    private static VistaController vc;

    /**
     * Clase que representa la ventana de baja de jugador.
     * Esta ventana permite al usuario seleccionar un jugador y proceder con su baja.
     */

    public VentanaBajaJugador(VistaController vc) {
        this.vc = vc;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

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
                    buttonOK.setEnabled(selectedItem != null && !selectedItem.equals("Selecciona un jugador..."));
                }
            }
        });
    }
    /*public void llenarComboBox(){
        ArrayList<Jugador> listaJugadores=vc.selectNicknameJugador();
        cJugador.removeAllItems();

        // Opción por defecto no válida
        cJugador.addItem("Selecciona un jugador...");

        for (Jugador jugador : listaJugadores) {
            cJugador.addItem(jugador.getNombre());
        }

        // Selecciona por defecto la opción inicial (índice 0)
        cJugador.setSelectedIndex(0);
    }*/

    private void onOK() {
        vc.bajaJugador(cJugador.getSelectedItem().toString());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VentanaBajaJugador dialog = new VentanaBajaJugador(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
