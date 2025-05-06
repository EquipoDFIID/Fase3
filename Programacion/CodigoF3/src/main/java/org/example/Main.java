package org.example;

import org.example.Controladores.ModeloController;
import org.example.Controladores.VistaController;
import org.example.Modelo.BD;

public class Main {
    public static void main(String[] args) {
        System.out.println(BD.getConnection());
        ModeloController mc = new ModeloController();
        mc.inscripcionCerrada();
        VistaController vc = new VistaController(mc);

        mc.setVista(vc);

    }
}