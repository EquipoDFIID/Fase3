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
     * @param nombreUsuario Nombre del usuario que se desea buscar.
     * @return Objeto `Usuario` si se encuentra en la base de datos, o `null` si no existe.
     */
    public Usuario selectUsuarioNom(String nombreUsuario, String clave) throws Exception {
        Usuario u= null;
        try {
            String sql = "SELECT * FROM USUARIOS WHERE LOWER(NOMBRE) = ? AND CLAVE=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setClave(rs.getString("clave"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                System.out.println(u.getNombre());
                return u;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Busca un usuario en la base de datos según su nombre.
     *
     * @param nickUsuario Nombre del usuario que se desea buscar.
     * @return Objeto `Usuario` si se encuentra en la base de datos, o `null` si no existe.
     */
    public Usuario selectUsuarioNick(String nickUsuario, String clave) throws Exception {
        Usuario u= null;
        try {
            String sql = "SELECT * FROM USUARIOS WHERE lower(NICKNAME) = ? AND CLAVE=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nickUsuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setClave(rs.getString("clave"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                System.out.println(u.getNombre());
                return u;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto `Usuario` que contiene los datos del usuario a crear.
     */
    public boolean crearUsuario (Usuario usuario) throws Exception {
        boolean creado = false;

        try {
            String sql = "INSERT INTO USUARIOS (nickname, nombre, clave, tipo_usuario) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNickname());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getTipoUsuario());
            ps.executeUpdate();
            creado = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return creado;
    }

    public boolean comprobarNickname(String nickname) throws Exception {
        boolean encontrado = false;

        try {
            String sql = "SELECT * FROM USUARIOS WHERE LOWER(nickname) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nickname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return encontrado;
    }
}