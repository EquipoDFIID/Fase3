package org.example.Modelo;

import org.example.Controladores.JugadorController;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO {
    static Connection con = BD.getConnection();

// =============================================
// == OPERACIONES DE CONSULTA (SELECT)
// =============================================

    public ArrayList<Jugador> selectIdNombreJugador(){
        ArrayList<Jugador> jugadores= new ArrayList<>();
        try {
            String sql = "SELECT id_jugador,nickname FROM EQUIPOS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt("id_jugador"));
                j.setNickname(rs.getString("nickname"));
                jugadores.add(j);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return jugadores;
    }

    public static Jugador buscarJugador(String nombreJugador) {
       Jugador j=null;
       j.setNombre(nombreJugador);
        try {
            String sql = "SELECT * FROM jugadores WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
           ResultSet rs= ps.executeQuery();
            if (rs.next()) {
                j.setIdJugador(rs.getInt("ID_JUGADOR"));
               j.setNombre(rs.getString("NOMBRE"));
               j.setApellido(rs.getString("APELLIDO"));
               j.setNacionalidad(rs.getString("NACIONALIDAD"));
                j.setFechaNacimiento(rs.getDate("FECHA_NAC").toLocalDate());
                j.setNickname(rs.getString("NICKNAME"));
                j.setSueldo(rs.getDouble("SUELDO"));
                //la linea de abajo puede dar problemas por que no se como se llama la columna del codigo de equipo y ademas el parametro de
                // "equipo" probablemente este mal
                j.setEquipo(EquipoDAO.buscarEquipo(rs.getString("EQUIPO")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return j;
    }

// =============================================
// == OPERACIONES DE INSERCIÓN (INSERT)
// =============================================

    public static void altaJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugadores VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jugador.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setInt(8, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ACTUALIZACIÓN (UPDATE)
// =============================================

    public static void modificarJugador(Jugador jugador, String nombreJugador) {
        try {
            String sql = "UPDATE JUGADORES SET id_jugador = ?,NOMBRE = ?," +
                         "apellido = ?,nacionalidad = ?,fecha_nac = ?,nickname = ?,sueldo = ?," +
                         "id_equipo = ? WHERE NOMBRE= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jugador.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setInt(8, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ELIMINACIÓN (DELETE)
// =============================================

    public static void borrarJugador(String nombreJugador) {
        try {
            String sql = "DELETE FROM JUGADORES WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
