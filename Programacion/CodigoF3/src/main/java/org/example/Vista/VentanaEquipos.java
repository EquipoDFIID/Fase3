package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase VentanaEquipos.
 * Representa una ventana gráfica que permite al usuario consultar la información
 * de los equipos disponibles en el sistema.
 */
public class VentanaEquipos extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea tEquipos;
    private JPanel pPrincipal;
    private VistaController vc;
    private JFrame ventanaUser;

    public VentanaEquipos(VistaController vc, String aNombre, JFrame ventanaUsuario) {
        try {
            this.vc = vc;
            this.ventanaUser = ventanaUsuario;
            setContentPane(pPrincipal);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("MenuPrincipal");
            setSize(680,580);
            setLocationRelativeTo(null);
            setResizable(true);
            iconoVentana();

            inicializarCampos();
            agregarListeners(aNombre);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() throws Exception{
        tEquipos.setText(EquipoDAO.procedimientoEquipos());
        tEquipos.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    public void agregarListeners(String aNombre){
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaUser.setVisible(true);
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
