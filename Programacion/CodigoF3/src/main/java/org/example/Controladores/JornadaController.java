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

    public Campeonato buscarCompeticion(int idCompeticion) {
        return campeonatoController.buscarCompeticion(idCompeticion);
    }

    public ArrayList<Jornada> selectObjetosJornada() {
        return listaJornadas;
    }

    // Método para generar las jornadas
    /*public void generarJornada() {
        StringBuilder sb = new StringBuilder();
        boolean correcto = true;
        List<Enfrentamiento> enfrentamientosDisponibles = new ArrayList<>(enfrentamientos);
        Collections.shuffle(enfrentamientosDisponibles);


        LocalDate fechaBase = LocalDate.now();
        int jornadaNum = 1;

        do {
            ArrayList<Enfrentamiento> enfrentamientosJornada = new ArrayList<>();
            sb.append("Generando Jornada ").append(jornadaNum).append(":\n");

            // Generamos los enfrentamientos por jornada
            for (int i = 1; i <= enfrentamientosDisponibles.size() / 2; i++) {
                if (enfrentamientosDisponibles.isEmpty()) {
                    correcto = false;
                    break;
                }

                Enfrentamiento enfrentamiento = enfrentamientosDisponibles.remove(0);
                enfrentamientosJornada.add(enfrentamiento);
                sb.append("  - Enfrentamiento: ").append(enfrentamiento.getEquipoAtacante().getNombre())
                        .append(" vs ").append(enfrentamiento.getEquipoDefensor().getNombre()).append("\n");
            }

            // Creamos la jornada con los enfrentamientos generados
            Jornada jornada = new Jornada();
            jornada.setIdJornada("Jornada" + jornadaNum);
            jornada.setFecha(fechaBase.plusDays(jornadaNum - 1).toString());
            jornada.setListaEnfrentamientos(enfrentamientosJornada);


            Campeonato campeonato = new Campeonato();
            jornada.setCampeonato(campeonato);

            listaJornadas.add(jornada);

            jornadaNum++; // Avanzamos al número siguiente de jornada
            JOptionPane.showMessageDialog(null, sb.toString(), "Jornadas Generadas", JOptionPane.INFORMATION_MESSAGE);
        } while (correcto);
    }*/

    public Jornada crearJornada(Jornada jornadaNueva) {
        listaJornadas.add(jornadaNueva);
        return dao.altaJornada(jornadaNueva);
    }
}