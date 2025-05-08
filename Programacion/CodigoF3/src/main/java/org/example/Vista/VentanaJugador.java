package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.JugadorDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VentanaJugador extends JFrame {
    private JPanel pPrincipal;
    private JTextArea tJugadores;
    private JButton bLogo;
    private JButton bSalir;
    private JComboBox cEquipos;
    private VistaController vc;
    private JFrame ventanaUsuario;
    private String nombre;

    public VentanaJugador(VistaController vc, String uNombre, JFrame ventanaUser) {
        this.vc = vc;
        this.nombre = uNombre;
        this.ventanaUsuario = ventanaUser;
        setContentPane(pPrincipal);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());

        tJugadores.setEditable(false);
        try {
            vc.llenarComboBoxE(cEquipos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventanaUsuario, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        tJugadores.setFont(new Font("Monospaced", Font.PLAIN, 12));
        cEquipos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedItem = (String) cEquipos.getSelectedItem();
                        if (!selectedItem.equals("Selecciona un equipo...")) {
                            tJugadores.setEditable(true);
                            tJugadores.setText(JugadorDAO.obtenerJugadoresPorEquipo(cEquipos.getSelectedItem().toString()));
                        } else {
                            tJugadores.setEditable(false);
                            tJugadores.setText("");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ventanaUsuario, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaUser.setVisible(true); // Vuelve a mostrar la ventana de administrador
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
