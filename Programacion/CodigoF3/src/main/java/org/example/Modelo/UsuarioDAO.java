package org.example.Modelo;


import org.example.Modelo.BD;
import org.example.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    static Connection con = BD.getConnection();
    /**
     * Busca un usuario en la base de datos según su nombre.
     *
     * @param nombre Nombre del usuario que se desea buscar.
     * @return Objeto `Usuario` si se encuentra en la base de datos, o `null` si no existe.
     */
    public Usuario selectNombre(String nombre){
        Usuario u= null;
        try {
            String sql = "SELECT * FROM USUARIOS WHERE INITCAP(nombre) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setClave(rs.getString("clave"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                System.out.println(u.getNombre());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    /**
     * Busca un usuario en la base de datos según su clave.
     *
     * @param clave Clave del usuario que se desea buscar.
     * @return Objeto `Usuario` si se encuentra en la base de datos, o `null` si no existe.
     */
     public Usuario selectClave(int clave){
        Usuario u= null;
        try {
            String sql = "SELECT * FROM USUARIOS WHERE clave = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setClave(rs.getString("clave"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto `Usuario` que contiene los datos del usuario a crear.
     */
    public void crearUsuario (Usuario usuario){
        try {
            String sql = "INSERT INTO USUARIOS (nombre, clave, tipo_usuario) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getTipoUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}