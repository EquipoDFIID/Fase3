package org.example.Modelo;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*     private String idEquipo;
    private String nombre;
    private LocalDate fechaFund;*/
public class EquipoDAO {
    public static ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private static Set<Integer> indicesUsados = new HashSet<>();

    public EquipoDAO(Connection con) {
    }

    public static void agregarEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
    }

    public static void borrarEquipo(Equipo i) {
        listaEquipos.remove(i);
    }

    public static Equipo buscarEquipo(String idEquipo) {
        boolean encontrado = true;
        Equipo equipo = null;
        for (int i = 0; (i < listaEquipos.size() && encontrado); i++) {
            if (listaEquipos.get(i).getIdEquipo().equals(idEquipo)) {
                equipo = listaEquipos.get(i);
                encontrado = false;
            }
        }
        return equipo;
    }



    public static void borrarJugador(Jugador jugador) {
        listaJugadores.remove(jugador);
    }

    public static Jugador buscarJugador(String idJugador) {
        boolean encontrado = true;
        Jugador jugador = null;
        for (int i = 0;(i < listaJugadores.size() && encontrado); i++) {
            if (listaJugadores.get(i).getIdJugador().equals(idJugador)) {
                borrarJugador(listaJugadores.get(i));
                encontrado = false;
            }
        }
        return jugador;
    }
    public static void agregadorModificarEquipo(Equipo eq) { listaEquipos.add(eq); }

    public static void a(){
        listaEquipos.add(new Equipo("E001", LocalDate.of(1902, 3, 6), "Real Madrid"));
        listaEquipos.add(new Equipo("E002", LocalDate.of(1899, 11, 29), "Barcelona"));
        listaEquipos.add(new Equipo("E003", LocalDate.of(1878, 3, 5), "Manchester United"));
        listaEquipos.add(new Equipo("E004", LocalDate.of(1900, 2, 27), "Bayern Múnich"));
        listaEquipos.add(new Equipo("E005", LocalDate.of(1897, 11, 1), "Juventus"));
        listaEquipos.add(new Equipo("E006", LocalDate.of(1892, 6, 3), "Liverpool"));
        listaEquipos.add(new Equipo("E007", LocalDate.of(1905, 3, 10), "Chelsea"));
        listaEquipos.add(new Equipo("E008", LocalDate.of(1970, 8, 12), "Paris Saint-Germain"));
        listaEquipos.add(new Equipo("E009", LocalDate.of(1908, 3, 9), "Inter de Milán"));
        listaEquipos.add(new Equipo("E010", LocalDate.of(1899, 12, 16), "AC Milan"));
    }

    public static Equipo buscarEquipoEnfrentamiento(){
        Random random = new Random();
        Equipo equipoSeleccionado = null;
        //int indiceAleatorio = random.nextInt(listaEquipos.size());
        boolean encontrado = false;
        int indiceAleatorio;

        do {
            indiceAleatorio = random.nextInt(listaEquipos.size());
            if (!indicesUsados.contains(indiceAleatorio)){
                equipoSeleccionado = listaEquipos.get(indiceAleatorio);
                encontrado = true;
            }
            indicesUsados.add(indiceAleatorio);
        }
        while(!encontrado);

        System.out.println(equipoSeleccionado.getNombre());

        return equipoSeleccionado;
    }
}