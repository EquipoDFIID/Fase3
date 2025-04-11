package org.example.Modelo;


import org.example.Modelo.BD;
import org.example.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    static Connection con = BD.getConnection();

    public Usuario selectNombre(String nombre){
        Usuario u= null;
        try {
            String sql = "SELECT * FROM USUARIOS WHERE id_equipo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("id_equipo"));
                u.setNombre(rs.getString("nombre"));
                u.setClave(rs.getString("clave"));
                u.setTipo_usuario(rs.getString("tipo_usuario"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
}