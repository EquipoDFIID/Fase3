package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    public VentanaIntroducirResultados(VistaController vc, String aNombre, JFrame ventanaAdmin) {
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

        // No volver a añadir el panel si ya está en el .form
        // add(panelEquipos, BorderLayout.CENTER); <-- Elimina esta línea si ya está en el .form

        vc.rellenarEquiposEnfrentamientos(panelEquipos);
        vc.llenarComboBoxJor(cJornada);

        bAceptar.setEnabled(false);



       /* cJornada.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = cJornada.getSelectedItem().toString();
                    if (!selectedItem.equals("Selecciona una jornada...")) {
                        bEquipo1.setEnabled(true);
                        bEquipo2.setEnabled(true);
                        vc.rellenarEquiposEnfrentamientos(panelEquipos);
                    }
                } else {
                    String selectedItem = cJornada.getSelectedItem().toString();
                    if (selectedItem.equals("Selecciona una jornada...")) {
                        bEquipo1.setEnabled(false);
                        bEquipo2.setEnabled(false);
                        defaultEquipos();
                    }
                }
            }
        });
        */

        /*
        bEquipo1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo3.setEnabled(true);
                    bEquipo4.setEnabled(true);
                } else {
                    bEquipo3.setEnabled(false);
                    bEquipo4.setEnabled(false);
                }
            }
        });

        bEquipo2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo3.setEnabled(true);
                    bEquipo4.setEnabled(true);
                } else {
                    bEquipo3.setEnabled(false);
                    bEquipo4.setEnabled(false);
                }
            }
        });

        bEquipo3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo5.setEnabled(true);
                    bEquipo6.setEnabled(true);
                } else {
                    bEquipo5.setEnabled(false);
                    bEquipo6.setEnabled(false);
                }
            }
        });

        bEquipo4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo5.setEnabled(true);
                    bEquipo6.setEnabled(true);
                } else {
                    bEquipo5.setEnabled(false);
                    bEquipo6.setEnabled(false);
                }
            }
        });

        bEquipo5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo7.setEnabled(true);
                    bEquipo8.setEnabled(true);
                } else {
                    bEquipo7.setEnabled(false);
                    bEquipo8.setEnabled(false);
                }
            }
        });

        bEquipo6.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo7.setEnabled(true);
                    bEquipo8.setEnabled(true);
                } else {
                    bEquipo7.setEnabled(false);
                    bEquipo8.setEnabled(false);
                }
            }
        });

        bEquipo7.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo9.setEnabled(true);
                    bEquipo10.setEnabled(true);
                } else {
                    bEquipo9.setEnabled(false);
                    bEquipo10.setEnabled(false);
                }
            }
        });

        bEquipo8.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bEquipo9.setEnabled(true);
                    bEquipo10.setEnabled(true);
                } else {
                    bEquipo9.setEnabled(false);
                    bEquipo10.setEnabled(false);
                }
            }
        });

        bEquipo9.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bAceptar.setEnabled(true);
                } else {
                    bAceptar.setEnabled(false);
                }
            }
        });

        bEquipo10.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bAceptar.setEnabled(true);
                } else {
                    bAceptar.setEnabled(false);

                }
            }
        });*/

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

   /* public void disableGanadores(){
        bEquipo1.setEnabled(false);
        bEquipo2.setEnabled(false);
        bEquipo3.setEnabled(false);
        bEquipo4.setEnabled(false);
        bEquipo5.setEnabled(false);
        bEquipo6.setEnabled(false);
        bEquipo7.setEnabled(false);
        bEquipo8.setEnabled(false);
        bEquipo9.setEnabled(false);
        bEquipo10.setEnabled(false);
    }

    public void defaultEquipos(){
        bEquipo1.setText("Equipo 1");
        bEquipo2.setText("Equipo 2");
        bEquipo3.setText("Equipo 3");
        bEquipo4.setText("Equipo 4");
        bEquipo5.setText("Equipo 5");
        bEquipo6.setText("Equipo 6");
        bEquipo7.setText("Equipo 7");
        bEquipo8.setText("Equipo 8");
        bEquipo9.setText("Equipo 9");
        bEquipo10.setText("Equipo 10");
    }*/


}
