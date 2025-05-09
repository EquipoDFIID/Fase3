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

    public void iconoVentana() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() {
        panelEquipos.setLayout(new GridLayout(0, 2, 10, 5));
        vc.llenarComboBoxJor(cJornada);
        bAceptar.setEnabled(false);
    }

    public void agregarListeners() {
        cJornada.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Comprobamos que el índice seleccionado no sea 0 (el valor predeterminado)
                    int selectedIndex = cJornada.getSelectedIndex();

                    if (selectedIndex > 0) { // Solo procesamos si no es la opción predeterminada
                        Object seleccionado = cJornada.getSelectedItem();

                        if (seleccionado instanceof Integer) {
                            int idJornada = (Integer) seleccionado;

                            try {
                                // Comprobamos si hay jornadas anteriores sin resultados
                                if (vc.hayJornadasAnterioresSinResultados(idJornada)) {
                                    JOptionPane.showMessageDialog(
                                            VentanaIntroducirResultados.this,
                                            "No puedes introducir resultados de esta jornada porque hay jornadas anteriores sin completar.",
                                            "Jornadas pendientes",
                                            JOptionPane.WARNING_MESSAGE
                                    );
                                    // Limpiar la vista si no es posible ingresar resultados
                                    panelEquipos.removeAll();
                                    panelEquipos.revalidate();
                                    panelEquipos.repaint();
                                    bAceptar.setEnabled(false);
                                } else {
                                    // Si la jornada es válida, cargamos los enfrentamientos
                                    vc.rellenarEquiposEnfrentamientos(panelEquipos, selectedIndex, bAceptar, VentanaIntroducirResultados.this);
                                    bAceptar.setEnabled(comprobarSelecciones());
                                    getRootPane().setDefaultButton(bAceptar);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(VentanaIntroducirResultados.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        // Si seleccionan "Selecciona una jornada", deshabilitamos el botón y limpiamos los enfrentamientos
                        panelEquipos.removeAll();
                        panelEquipos.revalidate();
                        panelEquipos.repaint();
                        bAceptar.setEnabled(false);
                    }
                }
            }

            /*
            cJornada.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        vc.rellenarEquiposEnfrentamientos(panelEquipos, cJornada.getSelectedIndex(), bAceptar, VentanaIntroducirResultados.this);
                        bAceptar.setEnabled(comprobarSelecciones());
                        getRootPane().setDefaultButton(bAceptar);
                    }
                }
            });
            */
        });

        /*
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
        */

        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object seleccionado = cJornada.getSelectedItem();
                if (!(seleccionado instanceof Integer)) {
                    JOptionPane.showMessageDialog(
                            VentanaIntroducirResultados.this,
                            "Por favor, selecciona una jornada válida.",
                            "Jornada inválida",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    int idJornada = (Integer) seleccionado;
                    try {
                        vc.procesarGanadoresSeleccionados(cJornada.getSelectedIndex());
                        JOptionPane.showMessageDialog(
                                VentanaIntroducirResultados.this,
                                "Resultados registrados correctamente."
                        );
                        ventanaAdministrador.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAdministrador.setVisible(true);
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

    public boolean comprobarSelecciones() {
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