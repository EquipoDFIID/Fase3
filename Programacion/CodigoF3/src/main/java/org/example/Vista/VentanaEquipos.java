package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.awt.*;
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
        setSize(680,580);
        setLocationRelativeTo(null);
        setResizable(true);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        try {
            tEquipos.setText(EquipoDAO.procedimientoEquipos());
            tEquipos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar los datos de los equipos");
        }




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
