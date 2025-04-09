package org.example.Modelo;

import org.example.Controladores.JugadorController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class JugadorDAO {
    static Connection con = BD.getConnection();


    public static boolean buscarJugador(String nombreJugador) {
        //si no es nulo devuelve true
        //si es nulo devuelve false
        boolean resultado = false;
        try {
            String sql = "SELECT * FROM jugadores WHERE NOMBRE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
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

    public static void altaJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugadores VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jugador.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setString(8, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void modificarJugador(Jugador jugador, String nombreJugador) {
        try {
            String sql = "UPDATE JUGADORES SET id_jugador = ?,NOMBRE = ?," +
                         "apellido = ?,nacionalidad = ?,fecha_nac = ?,nickname = ?,sueldo = ?," +
                         "id_equipo = ? WHERE NOMBRE= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jugador.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setString(8, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

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
