package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEquipos extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea tEquipos;
    private JPanel pPrincipal;
    private VistaController vc;
    private JFrame ventanaUser;

    public VentanaEquipos(VistaController vc, String nombre, JFrame ventanaUsuario) {
        this.vc = vc;
        this.ventanaUser = ventanaUsuario;
        setContentPane(pPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,580);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaUsuario ventanaUsuario = new VentanaUsuario(vc, nombre);
                ventanaUsuario.setVisible(true);
                dispose();
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
}
