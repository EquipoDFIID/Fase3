package org.example.Modelo;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO {
    static Connection con = BD.getConnection();

    public JugadorDAO() {
    }

// =============================================
// == OPERACIONES DE CONSULTA (SELECT)
// =============================================
    public ArrayList<Jugador> selectObjetosJugador() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
    /**
     * Obtiene una lista de jugadores con su ID y nickname.
     * @return Lista de objetos Jugador con ID y nickname.
     */
        try {
            String sql = "SELECT * FROM JUGADORES";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setNombre(rs.getString("NOMBRE"));
                j.setApellido(rs.getString("APELLIDO"));
                j.setNacionalidad(rs.getString("NACIONALIDAD"));
                j.setFechaNacimiento(rs.getDate("FECHA_NAC").toLocalDate());
                j.setNickname(rs.getString("NICKNAME"));
                j.setSueldo(rs.getDouble("SUELDO"));
                j.setEquipo(EquipoDAO.buscarEquipo(rs.getString("ID_EQUIPO")));
                jugadores.add(j);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return jugadores;
    }



    /**
     * Busca un jugador en la base de datos por su nombre.
     * @param nombreJugador Nombre del jugador a buscar.
     * @return Objeto Jugador con los detalles del jugador, o null si no se encuentra.
     */
    public static Jugador buscarJugador(String nombreJugador) {
       Jugador j = new Jugador();
       j.setNickname(nombreJugador);
        try {
            String sql = "SELECT * FROM JUGADORES WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                j.setIdJugador(rs.getInt("ID_JUGADOR"));
                j.setNombre(rs.getString("NOMBRE"));
                j.setApellido(rs.getString("APELLIDO"));
                j.setNacionalidad(rs.getString("NACIONALIDAD"));
                j.setFechaNacimiento(rs.getDate("FECHA_NAC").toLocalDate());
                j.setNickname(rs.getString("NICKNAME"));
                j.setSueldo(rs.getDouble("SUELDO"));
                j.setEquipo(EquipoDAO.buscarEquipo(rs.getString("ID_EQUIPO")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return j;
    }

// =============================================
// == OPERACIONES DE INSERCIÓN (INSERT)
// =============================================
    /**
     * Inserta un nuevo jugador en la base de datos.
     * @param jugador Objeto Jugador con los datos a insertar.
     */
    public static void altaJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugadores (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getApellido());
            ps.setString(3, jugador.getNacionalidad());
            ps.setDate(4, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNickname());
            ps.setDouble(6, jugador.getSueldo());
            ps.setInt(7, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ACTUALIZACIÓN (UPDATE)
// =============================================
    /**
     * Modifica los detalles de un jugador en la base de datos.
     * @param jugador Objeto Jugador con los nuevos datos.
     * @param jugadorAnterior Objeto del jugador para identificar el registro a modificar.
     */
    public static void modificarJugador(Jugador jugador, Jugador jugadorAnterior) {
        try {
            String sql = "UPDATE JUGADORES SET NOMBRE = ?," +
                         "apellido = ?,nacionalidad = ?,fecha_nac = ?,nickname = ?,sueldo = ?," +
                         "id_equipo = ? WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getApellido());
            ps.setString(3, jugador.getNacionalidad());
            ps.setDate(4, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNickname());
            ps.setDouble(6, jugador.getSueldo());
            ps.setInt(7, jugador.getEquipo().getIdEquipo());
            ps.setString(8, jugadorAnterior.getNickname());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ELIMINACIÓN (DELETE)
// =============================================
    /**
     * Elimina un jugador de la base de datos por su nombre.
     * @param nombreJugador Nombre del jugador a eliminar.
     */

    public static void borrarJugador(String nombreJugador) {
        try {
            String sql = "DELETE FROM JUGADORES WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static String obtenerJugadoresPorEquipo(String nombreEquipo) {
        StringBuilder tabla = new StringBuilder();
        CallableStatement cstmt = null;

        try {
            String sql = "{ call obtener_jugadores_equipo(?, ?) }";
            cstmt = con.prepareCall(sql);
            cstmt.setString(1, nombreEquipo); // Parámetro de entrada
            cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR); // Parámetro de salida (cursor)
            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(2)) {
                // Encabezados de la tabla
                tabla.append("| NOMBRE            | APELLIDO           | SUELDO     |\n");
                tabla.append("|-------------------|--------------------|------------|\n");

                // Procesar cada fila
                while (rs.next()) {
                    String nombreJugador = rs.getString("NOMBRE");
                    String apellidoJugador = rs.getString("APELLIDO");
                    double sueldo = rs.getDouble("SUELDO");

                    tabla.append(String.format("| %-17s | %-18s | %-10.2f |\n",
                            nombreJugador,
                            apellidoJugador,
                            sueldo));
                }
            }
        } catch (Exception ex) {
            tabla.append("Error al obtener los jugadores del equipo: ").append(ex.getMessage());
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
