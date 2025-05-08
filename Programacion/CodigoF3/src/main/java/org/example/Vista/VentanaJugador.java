package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Modelo.EquipoDAO;
import org.example.Modelo.JugadorDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VentanaJugador extends JFrame {
    private JPanel pPrincipal;
    private JButton button1;
    private JTextArea textArea1;
    private JButton SALIRButton;
    private JComboBox comboBox1;
    private VistaController vc;
    private JFrame ventanaUser;

    public VentanaJugador(VistaController vc, String nombre, JFrame ventanaUsuario) {
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
        vc.llenarComboBoxE(comboBox1);

        textArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) comboBox1.getSelectedItem();
                    if (!selectedItem.equals("Selecciona un equipo...")) {
                        textArea1.setText(JugadorDAO.obtenerJugadoresPorEquipo(comboBox1.getSelectedItem().toString()));
                    }
                }

            }
        });
    }
}
