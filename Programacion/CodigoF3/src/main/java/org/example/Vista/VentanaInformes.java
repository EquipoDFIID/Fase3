package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase VentanaInformes.
 * Representa una ventana gráfica que muestra informes y permite al usuario interactuar con el sistema.
 */
public class VentanaInformes extends JFrame {
    private JButton bLogo;
    private JButton bSalir;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private VistaController vc;
    private String nombre;
    private JFrame ventanaAdministrador;
    /**
     * Constructor de la clase VentanaInformes.
     *
     * @param vc Controlador de la vista que gestiona la lógica de la aplicación.
     * @param aNombre Nombre del usuario actual.
     * @param ventanaAdmin Ventana de administrador desde la que se accedió a esta ventana.
     */
    public VentanaInformes(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        this.vc = vc;
        this.nombre = aNombre;
        this.ventanaAdministrador = ventanaAdmin;
        setContentPane(pPrincipal);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
                dispose(); //
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
