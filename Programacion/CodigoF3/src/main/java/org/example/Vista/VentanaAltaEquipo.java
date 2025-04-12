package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaAltaEquipo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField eID;
    private JTextField eNombre;
    private JTextField eFecha;
    private static VistaController vc;
    private static VentanaAltaEquipo ventana;

    /**
     * Clase que representa la ventana de alta de equipo.
     * Esta ventana permite al usuario introducir los datos necesarios
     * para registrar un nuevo equipo en la aplicación.
     */
    public VentanaAltaEquipo(VistaController vc) {
        this.vc = vc;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonOK.setEnabled(false);

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

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        eID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (eID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El id no puede estar vacío");
                    eID.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[0-9]{4}$");
                    Matcher m = p.matcher(eID.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El id debe tener 4 dígitos");
                        eID.requestFocus();
                    }
                }
            }
        });

        eNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (eNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                    eNombre.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(eNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                        eNombre.requestFocus();
                    }
                }
            }
        });

        eFecha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = eFecha.getText();
                Pattern p = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                Matcher m = p.matcher(texto);

                if (m.matches()) {
                    buttonOK.setEnabled(true);
                } else {
                    buttonOK.setEnabled(false);
                }
            }
        });

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        VentanaAltaEquipo dialog = new VentanaAltaEquipo(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
