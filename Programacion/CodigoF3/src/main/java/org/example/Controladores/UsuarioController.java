package org.example.Controladores;

import org.example.Modelo.Usuario;
import org.example.Modelo.UsuarioDAO;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(UsuarioDAO usuarioDao) {
        this.usuarioDAO=usuarioDao;
    }

    public Usuario selectNombre(String nombreJugador){
        return usuarioDAO.selectNombre(nombreJugador);
    }

}
