package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDAO;

import java.sql.SQLException;

/**
 * Clase `CampeonatoController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los campeonatos, interactuando con la capa de datos (`CampeonatoDAO`).
 */
public class CampeonatoController {
    private static CampeonatoDAO campeonatoDao;

    public CampeonatoController(CampeonatoDAO campeonatoDao) {
        this.campeonatoDao = campeonatoDao;
    }

    public Campeonato buscarCompeticion(int idCampeonato) throws Exception {
        return campeonatoDao.buscarCompeticion(idCampeonato);
    }

    public void competicionUpdateInscripcion(String inscripcion) throws Exception {
        campeonatoDao.competicionUpdateInscripcion(inscripcion);

    }


}
