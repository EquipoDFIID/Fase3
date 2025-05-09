package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.Jornada;
import org.example.Modelo.JornadaDAO;

import java.util.ArrayList;
/**
 * Clase `JornadaController` que actúa como controlador para gestionar las operaciones
 * relacionadas con las jornadas, interactuando con la capa de datos (`JornadaDAO`).
 */
public class JornadaController {

    private static JornadaDAO dao;
    private static CampeonatoController campeonatoController;
    ArrayList<Jornada> listaJornadas = new ArrayList<>();

    public JornadaController(JornadaDAO jornadaDAO) {
        dao=jornadaDAO;
    }

    public Campeonato buscarCompeticion(int idCompeticion) throws Exception {
        return campeonatoController.buscarCompeticion(idCompeticion);
    }

    /**
     *
     * @return arraylist de todas las jornadas
     */
    public ArrayList<Jornada> selectObjetosJornada() {
        return listaJornadas;
    }

    /**
     *
     * @param jornadaNueva
     * @return una nueva jornada
     * @throws Exception
     */
    public Jornada crearJornada(Jornada jornadaNueva) throws Exception {
        listaJornadas.add(jornadaNueva);
        return dao.altaJornada(jornadaNueva);
    }

    public boolean hayJornadasAnterioresSinResultados(int idJornadaActual) throws Exception {
        return dao.hayJornadasAnterioresSinResultados(idJornadaActual);
    }
}