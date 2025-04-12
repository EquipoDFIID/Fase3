package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa la ventana de alta de jugador.
 * Esta ventana permite al usuario introducir los datos necesarios
 * para registrar un nuevo jugador en la aplicación.
 */
public class VentanaAltaJugador extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton button1;
    private JTextField jID;
    private JTextField jNickname;
    private JTextField jSueldo;
    private JTextField jNombre;
    private JTextField jApellido;
    private JTextField jNacionalidad;
    private JTextField jFecha;
    private static VistaController vc;

    /**
     * Constructor de la ventana de alta de jugador.
     * Inicializa los componentes de la interfaz gráfica, configura el comportamiento
     * de los botones y maneja las acciones de cierre de la ventana.
     * @param vc El controlador de la vista, que contiene la lógica de la aplicación.
     */

    private static VentanaAdministrador ventana;


    public VentanaAltaJugador(VistaController vc) {
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

        jID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (jID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El id no puede estar vacío");
                    jID.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[0-9]{4}$");
                    Matcher m = p.matcher(jID.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El id debe tener 4 dígitos");
                        jID.requestFocus();
                    }
                }
            }
        });

        jNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (jNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
                    jNombre.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
                    Matcher m = p.matcher(jNombre.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nombre debe comenzar por una mayúscula");
                        jNombre.requestFocus();
                    }
                }
            }
        });

        jApellido.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (e.isTemporary()) return;

                if (jApellido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El apellido no puede estar vacío");
                    jApellido.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
                    Matcher m = p.matcher(jApellido.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El apellido debe comenzar por una mayúscula y solo tener letras");
                        jApellido.requestFocus();
                    }
                }
            }
        });

        jNacionalidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (e.isTemporary()) return;

                if (jNacionalidad.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La nacionalidad no puede estar vacía");
                    jNacionalidad.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");
                    Matcher m = p.matcher(jNacionalidad.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "La nacionalidad debe comenzar por una mayúscula y solo tener letras");
                        jNacionalidad.requestFocus();
                    }
                }
            }
        });

        jFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (jFecha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La fecha no puede estar vacía");
                    jFecha.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                    Matcher m = p.matcher(jFecha.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "La fecha debe tener el formato dd/mm/aaaa");
                        jFecha.requestFocus();
                    }
                }
            }
        });

        jNickname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (e.isTemporary()) return;

                if (jNickname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nickname no puede estar vacío");
                    jNickname.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[a-zA-Z0-9_]{3,15}$");
                    Matcher m = p.matcher(jNickname.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El nickname debe tener entre 3 y 15 caracteres (letras, números o guion bajo)");
                        jNickname.requestFocus();
                    }
                }
            }
        });


        jSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (e.isTemporary()) return;

                if (jSueldo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El sueldo no puede estar vacío");
                    jSueldo.requestFocus();
                } else {
                    Pattern p = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
                    Matcher m = p.matcher(jSueldo.getText());
                    if (!m.matches()) {
                        JOptionPane.showMessageDialog(null, "El sueldo debe ser un número válido (puede tener decimales)");
                        jSueldo.requestFocus();
                    }
                }

                buttonOK.setEnabled(true);
            }
        });

        jSueldo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = jSueldo.getText();
                Pattern p = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
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
        dispose();
    }

    public static void main(String[] args) {
        VentanaAltaJugador dialog = new VentanaAltaJugador(vc);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
