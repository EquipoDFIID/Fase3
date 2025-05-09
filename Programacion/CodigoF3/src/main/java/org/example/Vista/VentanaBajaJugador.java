package org.example.Vista;

import org.example.Controladores.VistaController;
import org.example.Excepciones.DatoNoValido;

import javax.swing.*;
import java.awt.event.*;

public class VentanaBajaJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton bLogo;
    private JComboBox cJugador;
    private static VistaController vc;
    private static String nombre;
    private JFrame ventanaAdministrador;

    /**
     * Clase que representa la ventana de baja de jugador.
     * Esta ventana permite al usuario seleccionar un jugador y proceder con su baja.
     */

    public VentanaBajaJugador(VistaController vc, String aNombre, JFrame ventanaAdmin) {
        try {
            this.vc = vc;
            this.nombre = aNombre;
            this.ventanaAdministrador = ventanaAdmin;
            setContentPane(contentPane);
            setModal(true);
            getRootPane().setDefaultButton(buttonOK);
            setSize(500, 580);
            setLocationRelativeTo(null);
            setResizable(false);
            iconoVentana();

            inicializarCampos();
            agregarListeners();

            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos() throws Exception{
        vc.llenarComboBoxJ(cJugador);
        buttonOK.setEnabled(false);
    }

    public void agregarListeners(){
        cJugador.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) cJugador.getSelectedItem();
                    getRootPane().setDefaultButton(buttonOK);
                    buttonOK.setEnabled(selectedItem != null && !selectedItem.equals("Selecciona un jugador..."));
                }
            }
        });
        bLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                vc.mostrarVentanaInicio();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            boolean eliminado;
            eliminado = vc.bajaJugador(cJugador.getSelectedItem().toString());

            if (eliminado) {
                JOptionPane.showMessageDialog(VentanaBajaJugador.this, "Jugador eliminado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new DatoNoValido("Error al eliminar el Jugador");
            }

            ventanaAdministrador.setVisible(true);
            dispose();
        } catch (DatoNoValido error) {
            JOptionPane.showMessageDialog(VentanaBajaJugador.this, error.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VentanaBajaJugador.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        ventanaAdministrador.setVisible(true);
        dispose();
    }
}
