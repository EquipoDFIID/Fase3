package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;

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
            iconoVentana();

            inicializarCampos();
            agregarListeners();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos(){
        panelEquipos.setLayout(new GridLayout(0, 2, 10, 5));
        vc.llenarComboBoxJor(cJornada);
        bAceptar.setEnabled(false);
    }

    public void agregarListeners(){
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
                    boolean insertados;
                    insertados = vc.procesarGanadoresSeleccionados(cJornada.getSelectedIndex());

                    if (insertados) {
                        JOptionPane.showMessageDialog(VentanaIntroducirResultados.this, "Resultados registrados correctamente.");
                    } else {
                        throw new DatoNoValido("Error al insertar los resultados");
                    }

                    ventanaAdministrador.setVisible(true);
                    dispose();
                } catch (DatoNoValido error) {
                    JOptionPane.showMessageDialog(VentanaIntroducirResultados.this, error.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaIntroducirResultados.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

