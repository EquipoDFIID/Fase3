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

    public Usuario selectNombre(String nombreJugador){
        return usuarioDAO.selectNombre(nombreJugador);
    }

    public void crearCuenta(String nombre, String clave){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setTipoUsuario("user");
        usuarioDAO.crearUsuario(usuario);
    }
}
