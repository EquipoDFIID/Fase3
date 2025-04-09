package org.example.Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EquipoDAO {
    static Connection con = BD.getConnection();

    public static boolean buscarEquipo(String nombreEquipo) {
        //si no es nulo devuelve true
        //si es nulo devuelve false
        boolean resultado = false;
        try {
            String sql = "SELECT * FROM EQUIPOS WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreEquipo);
            ps.executeQuery();
            if (ps != null) {
                resultado = true;
            } else {
                resultado = false;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return resultado;
    }

    public static void altaEquipo(Equipo equipo) {
        try {
            String sql = "INSERT INTO equipos VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipo.getIdEquipo());
            ps.setString(2, equipo.getNombre());
            ps.setDate(3, Date.valueOf(equipo.getFechaFund()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void modificarEquipo(Equipo equipo, String idEquipo) {
        try {
            String sql = "UPDATE EQUIPOS SET id_equipo = ?,NOMBRE = ?,FECHA_FUND = ? WHERE NOMBRE= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipo.getIdEquipo());
            ps.setString(2, equipo.getNombre());
            ps.setDate(3, Date.valueOf(equipo.getFechaFund()));
            ps.setString(4, idEquipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void borrarEquipo(String nombreEquipo) {
        try {
            String sql = "DELETE FROM EQUIPOS WHERE ID_EQUIPO = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreEquipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}