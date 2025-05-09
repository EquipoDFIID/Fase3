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

    public Equipo selectEquipo(String id) throws Exception {
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




    public ArrayList<Equipo> selectObjetosEquipo() throws Exception {
        ArrayList<Equipo> equipos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setFechaFund(rs.getDate("fecha_fund").toLocalDate());
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

    public static Equipo buscarEquipo(String nombreEquipo) throws Exception {
        Equipo e = new Equipo();

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

    public static Equipo buscarEquipoInt(int nombreEquipo) throws Exception {
        Equipo e = new Equipo();

        try {
            String sql = "SELECT * FROM EQUIPOS WHERE ID_EQUIPO = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nombreEquipo);
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
    public int selectCountEquipos() throws Exception {
        int cantidad=0;

        try{
            String sql = "select count(*) from EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cantidad=rs.getInt(1);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return cantidad;
    }

// =============================================
// == OPERACIONES DE INSERCIÓN (INSERT)
// =============================================
    /**
     * Inserta un nuevo equipo en la base de datos.
     * @param equipo Objeto Equipo con los datos a insertar.
     */
    public static boolean altaEquipo(Equipo equipo) throws Exception {
        boolean insertado = false;
        try {
            String sql = "INSERT INTO equipos (NOMBRE, FECHA_FUND) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipo.getNombre());
            ps.setDate(2, Date.valueOf(equipo.getFechaFund()));
            ps.executeUpdate();
            insertado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return insertado;
    }

// =============================================
// == OPERACIONES DE ACTUALIZACIÓN (UPDATE)
// =============================================

    /**
     * Actualiza los datos de un equipo en la base de datos.
     * @param equipo Objeto Equipo con los nuevos datos.
     * @param equipoAnterior Objeto del equipo para identificar el registro a modificar.
     */

    public static boolean modificarEquipo(Equipo equipo, Equipo equipoAnterior) throws Exception {
        boolean modificado = false;

        try {
            String sql = "UPDATE EQUIPOS SET NOMBRE = ?,FECHA_FUND = ? WHERE NOMBRE= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipo.getNombre());
            ps.setDate(2, Date.valueOf(equipo.getFechaFund()));
            ps.setString(3, equipoAnterior.getNombre());
            ps.executeUpdate();
            modificado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modificado;
    }

// =============================================
// == OPERACIONES DE ELIMINACIÓN (DELETE)
// =============================================

    /**
     * Elimina un equipo de la base de datos según su ID.
     * @param e Objeto Equipo que se desea eliminar.
     */
    public static boolean borrarEquipo(Equipo e) throws Exception {
        boolean eliminado = false;
        try {
            String sql = "DELETE FROM EQUIPOS WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.executeUpdate();
            eliminado = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return eliminado;
    }

    public ArrayList selectAllEquipo() throws SQLException{
        ArrayList<Equipo> equipos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Equipo e = new Equipo();
                e.setIdEquipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setFechaFund(rs.getDate("fecha_fund").toLocalDate());
                equipos.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return equipos;
    }
    public static String procedimientoEquipos() throws Exception {
        StringBuilder tabla = new StringBuilder();
        CallableStatement cstmt = null;

        try {
            String sql = "{ call sp_informe_equipos_cursor(?) }";
            cstmt = con.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                // Encabezados de la tabla
                tabla.append("| EQUIPO            | FUNDACIÓN           | JUGADORES | SUELDO MÁX | SUELDO MÍN | PROMEDIO  |\n");
                tabla.append("|-------------------|---------------------|-----------|------------|------------|-----------|\n");

                // Procesar cada fila
                while (rs.next()) {
                    String nombreEquipo = rs.getString("nombre_equipo");
                    String fechaFundacion = rs.getString("fecha_fundacion");
                    int cantidadJugadores = rs.getInt("cantidad_jugadores");
                    double sueldoMax = rs.getDouble("sueldo_max");
                    double sueldoMin = rs.getDouble("sueldo_min");
                    double sueldoPromedio = rs.getDouble("sueldo_promedio");

                    tabla.append(String.format("| %-17s | %-18s | %-9d | %-10.2f | %-10.2f | %-9.2f |\n",
                            nombreEquipo,
                            fechaFundacion,
                            cantidadJugadores,
                            sueldoMax,
                            sueldoMin,
                            sueldoPromedio));
                }
            }
        } catch (Exception ex) {
            tabla.append("Error al generar la tabla: ").append(ex.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }

        return tabla.toString();
    }
}