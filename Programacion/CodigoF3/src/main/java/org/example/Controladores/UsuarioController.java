package org.example.Controladores;

import org.example.Modelo.Usuario;
import org.example.Modelo.UsuarioDAO;
/**
 * Clase `UsuarioController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los usuarios, interactuando con la capa de datos (`UsuarioDAO`).
 */
public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(UsuarioDAO usuarioDao) {
        this.usuarioDAO=usuarioDao;
    }

    public Usuario selectUsuarioNick(String nickUsuario, String clave) throws Exception {
        return usuarioDAO.selectUsuarioNick(nickUsuario.toLowerCase(), clave);
    }
    public Usuario selectUsuarioNom(String nombreUsuario, String clave) throws Exception {
        return usuarioDAO.selectUsuarioNom(nombreUsuario.toLowerCase(), clave);
    }
    public void crearCuenta(String nickname, String nombre, String clave) throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setTipoUsuario("user");
        usuarioDAO.crearUsuario(usuario);
    }

    public boolean comprobarNickname(String nickname)throws Exception {
        return usuarioDAO.comprobarNickname(nickname.toLowerCase());
    }
}
