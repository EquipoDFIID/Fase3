package org.example;

import org.example.Controladores.ModeloController;
import org.example.Controladores.VistaController;
import org.example.Vista.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        ModeloController mc=new ModeloController();

        VistaController vc=new VistaController();
        mc.setVista(vc);
    }
}