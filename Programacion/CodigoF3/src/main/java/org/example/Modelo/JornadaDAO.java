package org.example.Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JornadaDAO {
    static Connection con = BD.getConnection();
    public JornadaDAO() {
    }
    public static Jornada altaJornada(Jornada jornada) {

        try {
            String sql = "INSERT INTO JORNADAS (FECHA, ID_COMPETICION) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, new String[] { "ID_JORNADA" }); // permite recuperar la clave generada
            ps.setDate(1, Date.valueOf(jornada.getFecha()));
            ps.setInt(2, jornada.getCampeonato());
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
}
