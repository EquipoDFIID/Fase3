package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase VentanaEquipos.
 * Representa una ventana gráfica que muestra información sobre equipos y permite al usuario interactuar con el sistema.
 */
public class VentanaEquipos extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea tEquipos;
    private JPanel pPrincipal;
    private VistaController vc;
    private JFrame ventanaUser;

    /**
     * Constructor de la clase VentanaEquipos.
     *
     * @param vc Controlador de la vista que gestiona la lógica de la aplicación.
     * @param nombre Nombre del usuario actual.
     * @param ventanaUsuario Ventana de usuario desde la que se accedió a esta ventana.
     */
    public VentanaEquipos(VistaController vc, String nombre, JFrame ventanaUsuario) {
        try {
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

            tEquipos.setText(EquipoDAO.procedimientoEquipos());
            tEquipos.setFont(new Font("Monospaced", Font.PLAIN, 12));




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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
