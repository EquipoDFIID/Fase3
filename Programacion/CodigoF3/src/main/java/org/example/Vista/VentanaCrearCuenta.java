package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
/**
 * Clase que representa la ventana para crear una cuenta de usuario.
 * Permite al usuario ingresar una clave y confirmarla antes de proceder.
 */
public class VentanaCrearCuenta extends JFrame {
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField tfClave;
    private JTextField tfClave2;
    private JPanel pPrincipal;
    private static VistaController vc;
    /**
     * Método que comprueba si las claves ingresadas coinciden.
     * Si las claves coinciden, habilita el botón "OK", de lo contrario muestra un mensaje de error.
     */
    public void comprobarClaves() {
        String clave1 = tfClave.getText();
        String clave2 = tfClave2.getText();

        if (clave1.equals(clave2)) {
            buttonOK.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "El clave no es correcta");
        }
    }

    public VentanaCrearCuenta(VistaController vc) {
        this.vc = vc;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaCrearCuenta");
        setSize(500,550);
        setLocationRelativeTo(null);
        buttonOK.setEnabled(false);

        comprobarClaves();
    }
}
