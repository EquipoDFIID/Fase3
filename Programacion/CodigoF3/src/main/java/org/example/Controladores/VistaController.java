package org.example.Controladores;

import org.example.Modelo.Equipo;
import org.example.Modelo.Jugador;
import org.example.Vista.VentanaAdministrador;
import org.example.Vista.VentanaInicio;
import org.example.Vista.VentanaUsuario;

import java.util.ArrayList;

public class VistaController {
    private VentanaAdministrador ventanaAdministrador;
    private VentanaInicio ventanaInicio;
    private VentanaUsuario ventanaUsuario;
    private ModeloController modeloController;

    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        ventanaInicio = new VentanaInicio(this);
        ventanaInicio.setVisible(true);
    }
    public void mostrarAdministrador(){
        ventanaAdministrador = new VentanaAdministrador(this);
        ventanaAdministrador.setVisible(true);
        ventanaInicio.setVisible(false);
    }

    public void mostrarUsuario(){
        ventanaUsuario = new VentanaUsuario(this);
        ventanaUsuario.setVisible(true);
        ventanaInicio.setVisible(false);
    }
    public void altaEquipo(Equipo e){
         modeloController.altaEquipo(e);
    }
    public void bajaEquipo(String nombreEquipo){
        Equipo e=modeloController.buscarEquipo(nombreEquipo);
        if(e!=null)
            modeloController.bajaEquipo(e);
    }
    public void modificarEquipo(String nombreEquipo){
        Equipo e=modeloController.buscarEquipo(nombreEquipo);
        if(e!=null){
            modeloController.modificarEquipo(e, nombreEquipo);
        }

    }
    public void altaJugador(Jugador j){
        modeloController.altaJugador(j);
    }
    public Jugador buscarJugador(String nombreJugador) {
        return modeloController.buscarJugador(nombreJugador);
    }

    /*public void bajaJugador(String nombreJugador){
        Jugador j=modeloController.buscarJugador(nombreJugador);
        if(j!=null)
            modeloController.bajaJugador(nombreJugador);
    }

    public void modificarJugador(String nombreJugador){
        Jugador j=modeloController.buscarJugador(nombreJugador);
        if(j!=null)
            modeloController.modificarJugador(j, nombreJugador);
    }*/

    public void generarJornada(){}

    public void generarEnfrentamientos(){}

    public ArrayList<Equipo> selectNombreEquipo(){
        return modeloController.selectNombreEquipo();
    }
    public ArrayList <Jugador> selectNicknameJugador(){
        return modeloController.selectNicknameJugador();
    }

   /* public void prueba(){
       ArrayList<Equipo>equipos= modeloController.selectNombreEquipo();
       equipos=new ArrayList<>();
       Equipo equipo = null;
       equipos.add(equipo);
    }*/
}
