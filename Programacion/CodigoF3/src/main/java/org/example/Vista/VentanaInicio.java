package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
/**
 * Clase que representa la ventana de inicio de sesión del sistema.
 * Permite iniciar sesión como administrador o usuario, o crear una nueva cuenta.
 */
public class VentanaInicio extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton buttonImagen;
    private JButton bCrearCuenta;
    private JPanel jAdmin;
    private JPanel jUsuario;
    private JRadioButton administradorRadioButton;
    private JRadioButton usuarioRadioButton;
    private JTextField aNombre;
    private JTextField aClave;
    private JTextField uNickname;
    private JTextField uClave;
    private JButton uIniciarSesionButton;
    private JButton aIniciarSesionButton;
    private JLabel relleno;
    private JPanel buttons;
    private static VistaController vc;
    private String tipo;


    public VentanaInicio(VistaController vc) {
        this.vc = vc;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("VentanaInicio");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        iconoVentana();

        inicializarCampos();
        agregarListeners();
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }

    public void inicializarCampos(){
        jAdmin.setVisible(false);
        jUsuario.setVisible(false);
        aIniciarSesionButton.setEnabled(false);
        uIniciarSesionButton.setEnabled(false);
    }

    public void agregarListeners(){
        administradorRadioButton.addActionListener(e -> {
            relleno.setVisible(false);
            jAdmin.setVisible(true);
            jUsuario.setVisible(false);
            uNickname.setText("");
            uClave.setText("");
            relleno.setVisible(false);
            buttonImagen.setHorizontalAlignment(SwingConstants.LEFT);

            aClave.setEnabled(false);
            aNombre.requestFocus();
        });

        usuarioRadioButton.addActionListener(e -> {
            relleno.setVisible(false);
            jUsuario.setVisible(true);
            jAdmin.setVisible(false);
            aNombre.setText("");
            aClave.setText("");
            buttonImagen.setHorizontalAlignment(SwingConstants.LEFT);

            uClave.setEnabled(false);
            uNickname.requestFocus();
        });

        aNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarNombre(aNombre.getText())) {
                    aClave.setEnabled(true);
                } else {
                    aClave.setEnabled(false);
                }
            }
        });

        uNickname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (uNickname.getText().isEmpty()) {
                    bCrearCuenta.setEnabled(true);
                } else {
                    bCrearCuenta.setEnabled(false);
                    uIniciarSesionButton.setEnabled(false);
                }

                if (validarNombre(uNickname.getText())) {
                    uClave.setEnabled(true);
                } else {
                    uClave.setEnabled(false);
                }
            }
        });

        aClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarClave(aClave.getText())) {
                    aIniciarSesionButton.setEnabled(true);
                    getRootPane().setDefaultButton(aIniciarSesionButton); // Agregado
                } else {
                    aIniciarSesionButton.setEnabled(false);
                }
            }
        });


        uClave.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validarClave(uClave.getText())) {
                    uIniciarSesionButton.setEnabled(true);
                    getRootPane().setDefaultButton(uIniciarSesionButton); // Agregado
                } else {
                    uIniciarSesionButton.setEnabled(false);
                }
            }
        });

        aIniciarSesionButton.addActionListener(e -> {
            try {
                tipo = "admin";
                vc.selectUsuarioNom(aNombre.getText(), aClave.getText());
                if (vc.comprobarNombreClave(tipo)) {
                    vc.mostrarVentanaAdministrador(aNombre.getText());
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(VentanaInicio.this, "No se ha encontrado el administrador", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                    aNombre.setText("");
                    aClave.setText("");
                    aClave.setEnabled(false);
                    aNombre.requestFocus();
                    aIniciarSesionButton.setEnabled(false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        uIniciarSesionButton.addActionListener(e -> {
            try {
                tipo = "user";
                vc.selectUsuarioNick(uNickname.getText(), uClave.getText());
                if (vc.comprobarNombreClave(tipo)) {
                    vc.mostrarVentanaUsuario(uNickname.getText());
                    setVisible(false);
                    JOptionPane.showOptionDialog(VentanaInicio.this, "¡Bienvenido, " + uNickname.getText() + "!", "Acceso concedido",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"ENTRAR"}, "ENTRAR"
                    );
                } else {
                    JOptionPane.showMessageDialog(VentanaInicio.this, "No se ha encontrado el usuario", "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
                    uNickname.setText("");
                    uClave.setText("");
                    uClave.setEnabled(false);
                    uNickname.requestFocus();
                    uIniciarSesionButton.setEnabled(false);
                    bCrearCuenta.setEnabled(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bCrearCuenta.addActionListener(e -> {
            vc.mostrarVentanaCuenta(VentanaInicio.this);
            setVisible(false);
        });
    }

    /**
     * Valida que el nombre comience por mayúscula y solo tenga letras.
     * @param nombre Nombre a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) return false;
        Pattern pattern = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$");
        return pattern.matcher(nombre).matches();
    }
    /**
     * Valida que la clave tenga exactamente 4 dígitos numéricos.
     * @param clave Clave a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean validarClave(String clave) {
        Pattern pattern = Pattern.compile("^[0-9]{4}$");
        return pattern.matcher(clave).matches();
    }
}
