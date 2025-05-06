package org.example.Controladores;

import org.example.Modelo.Jornada;
import org.example.Modelo.JornadaDAO;

import java.util.ArrayList;

public class JornadaController {

    private static JornadaDAO dao;

    public JornadaController(JornadaDAO jornadaDao) {
        this.dao = jornadaDao;
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
        return dao.altaJornada(jornadaNueva);
    }
}