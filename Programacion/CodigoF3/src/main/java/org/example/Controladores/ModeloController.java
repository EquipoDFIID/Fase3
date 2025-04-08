package org.example.Controladores;

import org.example.Modelo.*;

import java.sql.Connection;

public class ModeloController {

    private CampeonatoDao campeonatoDao;
    private CampeonatoController campeonatoController;

    private EnfrentamientoController enfrentamientoController;
    private EnfrentamientoDAO enfrentamientoDao;

    private EquipoController equipoController;
    private EquipoDAO equipoDao;

    private JornadaController jornadaController;
    private JornadaDAO jornadaDao;

    private JugadorController jugadorController;
    private JugadorDAO jugadorDao;

    private VistaController vc;

    public ModeloController() {
        try{
            BaseDatos.abrirConexion();
            Connection con =BaseDatos.getCon();

            campeonatoDao=new CampeonatoDao(con);
            campeonatoController= new CampeonatoController(campeonatoDao);

            enfrentamientoDao=new EnfrentamientoDAO(con);
            enfrentamientoController=new EnfrentamientoController(enfrentamientoDao);

            equipoDao=new EquipoDAO(con);
            equipoController=new EquipoController(equipoDao);

            jornadaDao=new JornadaDAO(con);
            jornadaController=new JornadaController(jornadaDao);

            jugadorDao=new JugadorDAO(con);
            jugadorController=new JugadorController(jugadorDao);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setVista(VistaController vc) {
        this.vc=vc;
    }
}
