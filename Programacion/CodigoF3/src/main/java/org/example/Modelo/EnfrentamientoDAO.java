package org.example.Modelo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class EnfrentamientoDAO {
    static List<Enfrentamiento> enfrentamientos=new ArrayList<>();

    public EnfrentamientoDAO(Connection con) {
    }

    public static void agregarEnfrentamientos (Enfrentamiento enfrentamiento){
        enfrentamientos.add(enfrentamiento);
    }

}