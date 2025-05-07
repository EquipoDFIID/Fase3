package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDAO;
/**
 * Clase `CampeonatoController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los campeonatos, interactuando con la capa de datos (`CampeonatoDAO`).
 */
public class CampeonatoController {
    private static CampeonatoDAO campeonatoDao;

    public CampeonatoController(CampeonatoDAO campeonatoDao) {
    }

    public Campeonato buscarCompeticion(int idCampeonato) {
        return campeonatoDao.buscarCompeticion(idCampeonato);
    }
}
