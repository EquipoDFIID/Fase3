package org.example.Controladores;

import org.example.Modelo.Equipo;
import org.example.Modelo.EquipoDAO;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase `EquipoController` que actúa como controlador para gestionar las operaciones
 * relacionadas con los equipos, interactuando con la capa de datos (`EquipoDAO`).
 */
public class EquipoController {
    private EquipoDAO equipoDAO;
    private Equipo eb;
    public EquipoController(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

    public void buscarEquipo(String nombre) throws Exception {
        eb = equipoDAO.buscarEquipo(nombre);
    }

    public boolean altaEquipo(String nombre, LocalDate fecha) throws Exception {
        Equipo e = new Equipo();
        e.setNombre(nombre);
        e.setFechaFund(fecha);
        return equipoDAO.altaEquipo(e);
    }
    public boolean bajaEquipo() throws Exception {
        return equipoDAO.borrarEquipo(eb);
    }
    public boolean modificarEquipo(String nombre, LocalDate fecha) throws Exception {
        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setFechaFund(fecha);
        return equipoDAO.modificarEquipo(equipo, eb);
    }

    public ArrayList<Equipo> selectObjetosEquipo() throws Exception{
        return equipoDAO.selectObjetosEquipo();
    }

    public int selectCountEquipos() throws Exception {
        return equipoDAO.selectCountEquipos();
    }

    public ArrayList selectAllEquipos() throws Exception {
        return equipoDAO.selectAllEquipo();
    }
}