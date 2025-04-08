package org.example;

import org.example.Modelo.BD;
import org.example.Vista.MenuPrincipal;
import org.example.Vista.VentanaInicio;

import javax.swing.*;

public class Main {
    private static MenuPrincipal mPrincipal=new MenuPrincipal();
    private static VentanaInicio mVentanaInicio=new VentanaInicio();
    public static void main(String[] args) {
        mVentanaInicio.setVisible(true);
        mPrincipal.setVisible(true);
        System.out.println(BD.getConnection());
    }
}