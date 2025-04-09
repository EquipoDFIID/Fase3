package org.example.Modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EquipoDAO {
    static Connection con = BD.getConnection();

// =============================================
// == OPERACIONES DE CONSULTA (SELECT)
// =============================================

    public Equipo selectEquipo(String id) {
        Equipo e= null;
        try {
            String sql = "SELECT * FROM EQUIPOS WHERE id_equipo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setFechaFund(rs.getDate("fecha_fund").toLocalDate());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    public ArrayList<Equipo> selectIdNombreEquipo(){
        ArrayList<Equipo> equipos= new ArrayList<>();

        try {
            String sql = "SELECT id_equipo,nombre FROM EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                equipos.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipos;
    }

    public static Equipo buscarEquipo(String nombreEquipo) {
        Equipo e= null;
        e.setNombre(nombreEquipo);
        try {
            String sql = "SELECT * FROM EQUIPOS WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreEquipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setFechaFund(rs.getDate("fecha_fund").toLocalDate());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

// =============================================
// == OPERACIONES DE INSERCIÓN (INSERT)
// =============================================

    public static void altaEquipo(Equipo equipo) {
        try {
            String sql = "INSERT INTO equipos VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, equipo.getIdEquipo());
            ps.setString(2, equipo.getNombre());
            ps.setDate(3, Date.valueOf(equipo.getFechaFund()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ACTUALIZACIÓN (UPDATE)
// =============================================

    public static void modificarEquipo(Equipo equipo, String nombreEquipo) {
        try {
            String sql = "UPDATE EQUIPOS SET id_equipo = ?,NOMBRE = ?,FECHA_FUND = ? WHERE NOMBRE= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, equipo.getIdEquipo());
            ps.setString(2, equipo.getNombre());
            ps.setDate(3, Date.valueOf(equipo.getFechaFund()));
            ps.setString(4, nombreEquipo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ELIMINACIÓN (DELETE)
// =============================================

    public static void borrarEquipo(Equipo e) {
        try {
            String sql = "DELETE FROM EQUIPOS WHERE ID_EQUIPO = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}