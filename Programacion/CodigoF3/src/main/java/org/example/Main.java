package org.example;

import org.example.Controladores.ModeloController;
import org.example.Controladores.VistaController;
import org.example.Modelo.BD;
import org.example.Modelo.EquipoDAO;

public class Main {
    public static void main(String[] args) throws Exception {

        ModeloController mc = new ModeloController();
        VistaController vc = new VistaController(mc);
        mc.competicionUpdateInscripcion("inscripcion");
        mc.setVista(vc);

    }
}