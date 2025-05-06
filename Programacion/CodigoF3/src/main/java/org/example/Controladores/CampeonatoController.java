package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDAO;

public class CampeonatoController {
    private static CampeonatoDAO campeonatoDao;

    public CampeonatoController(CampeonatoDAO campeonatoDao) {
    }

    public Campeonato buscarCompeticion(int idCampeonato) {
        return campeonatoDao.buscarCompeticion(idCampeonato);
    }
}
