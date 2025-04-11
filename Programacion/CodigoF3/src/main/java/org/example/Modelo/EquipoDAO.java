package org.example.Modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EquipoDAO {
    static Connection con = BD.getConnection();

    public EquipoDAO() {
    }

// =============================================
// == OPERACIONES DE CONSULTA (SELECT)
// =============================================
    /**
     * Busca un equipo en la base de datos según su ID.
     * @param id ID del equipo que se desea buscar.
     * @return Objeto Equipo si se encuentra, o null si no existe.
     */

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
    /**
     * Obtiene una lista de todos los equipos con su ID y nombre.
     * @return Lista de objetos Equipo con ID y nombre.
     */




    public ArrayList<Equipo> selectNombreEquipo(){
        ArrayList<Equipo> equipos = new ArrayList<>();

        try {
            String sql = "SELECT nombre FROM EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Equipo e = new Equipo();
                e.setNombre(rs.getString("nombre"));
                equipos.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipos;
    }


    /**
     * Busca un equipo por su nombre en la base de datos.
     * @param nombreEquipo Nombre del equipo a buscar.
     * @return Objeto Equipo si se encuentra, o null si no existe.
     */

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
    /**
     * Inserta un nuevo equipo en la base de datos.
     * @param equipo Objeto Equipo con los datos a insertar.
     */
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

    /**
     * Actualiza los datos de un equipo en la base de datos.
     * @param equipo Objeto Equipo con los nuevos datos.
     * @param nombreEquipo Nombre actual del equipo para identificar el registro a modificar.
     */

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

    /**
     * Elimina un equipo de la base de datos según su ID.
     * @param e Objeto Equipo que se desea eliminar.
     */
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