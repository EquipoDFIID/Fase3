package org.example.Modelo;

import java.sql.Connection;
import java.util.ArrayList;

public class CampeonatoDao {
    static ArrayList<Campeonato> campeonatos = new ArrayList<>();

    public CampeonatoDao(Connection con) {
    }

    public static void  agregarCampeonato(Campeonato campeonato){
        campeonatos.add(campeonato);
    }

}
