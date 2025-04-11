package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaCrearCuenta extends JFrame {
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField tfClave;
    private JTextField tfClave2;
    private JPanel pPrincipal;
    private static VistaController vc;

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
        setSize(500,580);
        setLocationRelativeTo(null);
        buttonOK.setEnabled(false);
        setResizable(false);

        comprobarClaves();
    }
}
