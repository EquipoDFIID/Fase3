package org.example.Modelo;

import org.example.Controladores.JornadaController;

import java.sql.*;
import java.util.ArrayList;

public class JornadaDAO {
    static Connection con = BD.getConnection();

    public JornadaDAO() {
    }
    public static Jornada altaJornada(Jornada jornada) {

        try {
            String sql = "INSERT INTO JORNADAS (FECHA, ID_COMPETICION) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, new String[] { "ID_JORNADA" }); // permite recuperar la clave generada
            ps.setDate(1, Date.valueOf(jornada.getFecha()));
            ps.setInt(2, jornada.getCampeonato().getID());
            ps.executeUpdate();

            // Recuperar el ID generado
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                jornada.setIdJornada(idGenerado); // asignar el ID al objeto Jornada
            }

        } catch (Exception e) {
            System.out.println("Error al insertar jornada: " + e.getMessage());
        }
        return jornada;
    }

    public static Jornada buscarJornada(int idJornada) {
        Jornada j = new Jornada();

        try {
            String sql = "SELECT * FROM JORNADAS WHERE ID_JORNADA = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idJornada);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                j.setIdJornada(rs.getInt("ID_JORNADA"));
                j.setFecha(rs.getDate("FECHA").toLocalDate());
                j.setCampeonato(CampeonatoDAO.buscarCompeticion(rs.getInt("ID_COMPETICION")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return j;
    }

    public ArrayList selectAllJornada() {
        ArrayList<Jornada> jornada = new ArrayList<>();

        try {
            String sql = "SELECT * FROM JORNADAS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Jornada j = new Jornada();
                j.setIdJornada(rs.getInt("ID_JORNADA"));
                j.setFecha(rs.getDate("FECHA").toLocalDate());
                j.setCampeonato(CampeonatoDAO.buscarCompeticion(rs.getInt("ID_COMPETICION")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return jornada;
    }
}
