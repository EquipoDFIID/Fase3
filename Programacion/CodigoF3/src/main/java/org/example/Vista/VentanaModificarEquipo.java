package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.Equipo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaModificarEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField3;
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
        ventana.setVisible(true);
        setVisible(false);
    }
    public void llenarComboBox(){
        ArrayList<Equipo> listaEquipos=vc.selectNombreEquipo();
        cNombre.removeAllItems();

        for (Equipo equipo : listaEquipos){
            cNombre.addItem(equipo.getNombre());
        }
    }

    public static void main(String[] args) {
        VentanaModificarEquipo dialog = new VentanaModificarEquipo(vc, ventana);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
