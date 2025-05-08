package org.example.Controladores;

import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Equipo;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.Modelo.EquipoDAO;
import org.example.Vista.VentanaAltaEquipo;
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


    public void altaEquipo(String nombre, LocalDate fecha) throws Exception {
        Equipo e = new Equipo();
        e.setNombre(nombre);
        e.setFechaFund(fecha);
        equipoDAO.altaEquipo(e);
    }
    public void bajaEquipo() throws Exception {
        equipoDAO.borrarEquipo(eb);
    }
    public void modificarEquipo(String nombre, LocalDate fecha) throws Exception {
        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setFechaFund(fecha);
        equipoDAO.modificarEquipo(equipo, eb);
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


   /* public void bajaEquipo() {
        boolean correcto=false;
        String codigo="";
        try {
            codigo = JOptionPane.showInputDialog("Ingrese el codigo del equipo que desea borrar");
            for(int i = 0; i<listaEquipos.size() && !correcto; i++){
                if(listaEquipos.get(i).getIdEquipo().equals(codigo)){
                    correcto=true;
                    EquipoDAO.borrarEquipo(listaEquipos.get(i));
                    JOptionPane.showMessageDialog(null, "El equipo se borrado correctamente");
                }
            }if(!correcto){
                JOptionPane.showMessageDialog(null, "El equipo no se ha encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al borrar el equipo");
        }

    }
    public Equipo modificarEquipo(){
        Equipo eq = null;
        try {

            String id = JOptionPane.showInputDialog("Ingrese el id del equipo que desea modificar");
            Optional<Equipo> equipoToModify = listaEquipos.stream()
                    .filter(equipo -> equipo.getIdEquipo().equals(id))
                    .findFirst();
            if (equipoToModify.isPresent()) {

                String nombre = solicitarDato("Nombre", "Teclea el nombre del equipo", "^[0-9a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\\\s]{3,15}$");
                LocalDate fechaFund = solicitarFecha();
                eq = new Equipo(id,fechaFund,nombre);
                EquipoDAO.agregadorModificarEquipo(eq);

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al modificar el equipo");
        }
        return eq;

    } */

    private LocalDate convertirFecha(String fechaTexto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(fechaTexto, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}