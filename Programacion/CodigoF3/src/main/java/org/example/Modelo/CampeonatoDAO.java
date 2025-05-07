package org.example.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CampeonatoDAO {
    static Connection con = BD.getConnection();
    public CampeonatoDAO() {
    }

    public static Campeonato buscarCompeticion(int idCompeticion) {
        Campeonato c = new Campeonato();

        try {
            String sql = "SELECT * FROM COMPETICIONES WHERE ID_COMPETICION = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCompeticion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c.setID(rs.getInt("ID_COMPETICION"));
                c.setNombre(rs.getString("NOMBRE"));
                c.setEstado(rs.getString("ESTADO"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

}
