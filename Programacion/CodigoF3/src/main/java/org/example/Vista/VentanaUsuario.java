package org.example.Vista;

import org.example.Controladores.VistaController;

import javax.swing.*;

public class VentanaUsuario extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton resultadosDeÚltimaJornadaButton;
    private VistaController vistaController;

    public VentanaUsuario(VistaController vistaController) {
        this.vistaController = vistaController;
    }
}
