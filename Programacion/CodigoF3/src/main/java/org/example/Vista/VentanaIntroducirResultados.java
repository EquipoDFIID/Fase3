package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class VentanaIntroducirResultados extends JFrame {
    private JPanel panel1;
    private JButton bLogo;
    private JButton bAceptar;
    private JPanel pPrincipal;
    private JButton bSalir;
    private JComboBox cJornada;
    private JPanel panelEquipos;
    private VistaController vc;
    private String nombre;
    private JFrame ventanaAdministrador;
    private ArrayList<ButtonGroup> gruposEquipos = new ArrayList<>();

    public VentanaIntroducirResultados(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        try {
            this.vc = vc;
            this.nombre = aNombre;
            this.ventanaAdministrador = ventanaAdmin;
            setContentPane(pPrincipal);
            setSize(500, 580);
            setLocationRelativeTo(null);
            setResizable(false);

            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon.png")));
            setIconImage(icon.getImage());

            // Usa layout si y solo si NO usas el GUI Designer
            panelEquipos.setLayout(new GridLayout(0, 2, 10, 5));

            //vc.rellenarEquiposEnfrentamientos(panelEquipos, 0, bAceptar, this);
            vc.llenarComboBoxJor(cJornada);
            //Quiero que la jornada al seleccionarla haga una select y luego salgan los radioButtons de esa jornada

            bAceptar.setEnabled(false);


            cJornada.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        vc.rellenarEquiposEnfrentamientos(panelEquipos, cJornada.getSelectedIndex(), bAceptar, VentanaIntroducirResultados.this);
                        bAceptar.setEnabled(comprobarSelecciones());
                        getRootPane().setDefaultButton(bAceptar); // Agregado
                    }
                }
            });


            bAceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        vc.procesarGanadoresSeleccionados(cJornada.getSelectedIndex());
                        JOptionPane.showMessageDialog(VentanaIntroducirResultados.this, "Resultados registrados correctamente.");
                        ventanaAdministrador.setVisible(true); // Vuelve a mostrar la ventana de administrador
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean comprobarSelecciones(){
        ArrayList<ButtonGroup> gruposEquipos = vc.pasarGrupos();

        boolean todosSeleccionados = true;
        for (ButtonGroup grupo : gruposEquipos) {
            if (grupo.getSelection() == null) {
                todosSeleccionados = false;
                break;
            }
        }

        return todosSeleccionados;
    }
}

