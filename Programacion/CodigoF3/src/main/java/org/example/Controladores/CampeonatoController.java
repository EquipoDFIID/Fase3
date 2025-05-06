package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDao;

import javax.swing.*;
import java.time.LocalDate;

public class CampeonatoController {
    private static CampeonatoDao campeonatoDao;

    public CampeonatoController(CampeonatoDao campeonatoDao) {
    }

    public Campeonato buscarCompeticion(int idCampeonato) {
        return campeonatoDao.buscarCompeticion(idCampeonato);
    }
}
