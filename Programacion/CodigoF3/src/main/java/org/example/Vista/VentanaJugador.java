package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.JugadorDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * Comprueba si todos los grupos de botones tienen una selección.
 *
 * @return true si todos los grupos tienen una selección, false en caso contrario.
 */
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
        try {
            this.vc = vc;
            this.nombre = uNombre;
            this.ventanaUsuario = ventanaUser;
            setContentPane(pPrincipal);
            setSize(500, 580);
            setLocationRelativeTo(null);
            setResizable(false);
            iconoVentana();

            inicializarCampos();
            agregarListeners(ventanaUser);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() throws Exception{
        tJugadores.setEditable(false);
        vc.llenarComboBoxE(cEquipos);
        tJugadores.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    public void agregarListeners(JFrame ventanaUser){
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
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
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
