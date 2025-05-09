package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase VentanaResultados.
 * Representa una ventana gráfica que muestra los resultados de un procedimiento
 * y permite al usuario interactuar con el sistema.
 */
public class VentanaResultados extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private VistaController vc;
    private JFrame ventanaUser;

    public VentanaResultados(VistaController vc, String nombre, JFrame ventanaUsuario) {
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

        try{
            textArea1.setText(vc.mostrarProcedimientoResultado());
            textArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
