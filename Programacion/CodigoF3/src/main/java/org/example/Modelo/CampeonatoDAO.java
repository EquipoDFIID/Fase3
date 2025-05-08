package org.example.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CampeonatoDAO {
    static Connection con = BD.getConnection();
    public CampeonatoDAO() {
    }

    public static Campeonato buscarCompeticion(int idCompeticion) throws Exception {
        Campeonato c = new Campeonato();

            String sql = "SELECT * FROM COMPETICIONES WHERE ID_COMPETICION = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCompeticion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c.setID(rs.getInt("ID_COMPETICION"));
                c.setNombre(rs.getString("NOMBRE"));
                c.setEstado(rs.getString("ESTADO"));
            }

        return c;
    }


    public void competicionUpdateInscripcion(String estado) throws Exception {

            String sql = "UPDATE COMPETICIONES SET ESTADO = ? WHERE ID_COMPETICION = 2";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.executeUpdate();
        }
    }

