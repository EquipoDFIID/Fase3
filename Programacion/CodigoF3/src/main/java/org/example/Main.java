package org.example;

import org.example.Controladores.ModeloController;
import org.example.Controladores.VistaController;

public class Main {
    public static void main(String[] args) {
        ModeloController mc = new ModeloController();
        VistaController vc = new VistaController(mc);
        mc.competicionUpdateInscripcion("inscripcion");
        mc.setVista(vc);

    }
}
