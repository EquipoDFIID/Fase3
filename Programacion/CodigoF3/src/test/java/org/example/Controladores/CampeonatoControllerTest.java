package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampeonatoControllerTest {
    private static CampeonatoDAO campeonatoDAO;
    private static CampeonatoController campeonatoController;

    @BeforeAll
    static void setUp() throws Exception {
        campeonatoDAO = new CampeonatoDAO();
        campeonatoController = new CampeonatoController(campeonatoDAO);

    }

    @Test
    void buscarCompeticion() {
        Campeonato campeonato = campeonatoController.buscarCompeticion(2);
        assertNotNull(campeonato);
        assertEquals(2, campeonato.getID());
        assertNotNull(campeonato.getNombre());
    }

    @Test
    void competicionUpdateInscripcion() {
        campeonatoController.competicionUpdateInscripcion("finalizada");
        Campeonato campeonato = campeonatoController.buscarCompeticion(2);
        assertEquals("finalizada", campeonato.getEstado());

    }
}