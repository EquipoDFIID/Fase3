package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase VentanaResultadosA.
 * Representa una ventana gráfica que muestra los resultados de un procedimiento
 * y permite al administrador interactuar con el sistema.
 */
public class VentanaResultadosA extends JFrame {
    private JButton bLogo;
    private JButton SALIRButton;
    private JTextArea textArea1;
    private JPanel pPrincipal;
    private VistaController vc;
    private JFrame ventanaAdmin;

    public VentanaResultadosA(VistaController vc, String nombre, JFrame ventanaAdmin) {
        try{
            this.vc = vc;
            this.ventanaAdmin = ventanaAdmin;
            setContentPane(pPrincipal);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("MenuPrincipal");
            setSize(680,580);
            setLocationRelativeTo(null);
            setResizable(true);
            iconoVentana();

            inicializarCampos();
            agregarListeners(nombre);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void iconoVentana(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(icon.getImage());
    }
    /**
     * Inicializa los campos de la ventana.
     * Muestra los resultados en el área de texto.
     *
     * @throws Exception Si ocurre un error al obtener los resultados.
     */
    public void inicializarCampos() throws Exception{
        textArea1.setText(vc.mostrarProcedimientoResultado());
        textArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }
    /**
     * Agrega los listeners a los componentes de la ventana.
     * Maneja eventos como salir de la ventana o volver a la ventana de inicio.
     *
     * @param nombre Nombre del administrador actual.
     */
    public void agregarListeners(String nombre) {
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAdmin.setVisible(true);
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
}
