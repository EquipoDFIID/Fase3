package org.example.Vista;



import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public JPanel panel1;
    private JButton altaDeEquipoButton;
    private JButton bajaDeEquipoButton;
    private JButton modificarEquipoButton;
    private JButton altaDeJugadorButton;
    private JButton modificarJugadorButton;
    private JButton generarJornadaButton;
    private JButton generarEnfrentamientosButton;
    private JButton TERMINARButton;
    private JButton bajaDeJugadorButton;

    public MenuPrincipal() throws HeadlessException {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MenuPrincipal");
        setSize(500,500);
       setLocationRelativeTo(null);



    }
}
