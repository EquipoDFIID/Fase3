package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;
/**
 * Clase VentanaModificarJugador.
 * Ventana de diálogo para modificar los datos de un jugador existente.
 * Permite seleccionar un jugador desde un comboBox y editar sus datos.
 */
public class VentanaModificarJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JPanel pPrincipal;
    private static VistaController vc;
    private static VentanaAdministrador ventana;

    public VentanaModificarJugador(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setSize(500, 550);

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

        // Aquí podés cargar los jugadores en el comboBox
        // Ejemplo:
        // comboBox1.addItem("Jugador 1");
        // comboBox1.addItem("Jugador 2");
    }

    private void onOK() {
        // Lógica para modificar al jugador seleccionado
        // Podés acceder al comboBox con comboBox1.getSelectedItem()
        // Y a los textos modificados con textField2.getText(), etc.

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
