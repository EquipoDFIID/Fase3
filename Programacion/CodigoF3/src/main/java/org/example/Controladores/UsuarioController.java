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

    public Usuario selectUsuario(String nombreJugador, String clave) {
        return usuarioDAO.selectUsuario(nombreJugador, clave);
    }

    public void crearCuenta(String nombre, String clave, String nickname){
        Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setTipoUsuario("user");
        usuarioDAO.crearUsuario(usuario);
    }

    public boolean comprobarNickname(String nickname) {
        return usuarioDAO.comprobarNickname(nickname.toLowerCase());
    }
}
