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
    private static VentanaAdministrador ventana;

    public VentanaBajaEquipo(VistaController vc) {
        this.vc = vc;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

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



    }

    private void onOK() {
        // add your code here
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

    public void llenarComboBox(){
        ArrayList<Equipo> listaEquipos=vc.selectNombreEquipo();
        cNombre.removeAllItems();

        for (Equipo equipo : listaEquipos){
            cNombre.addItem(equipo.getNombre());
        }
    }
}
