package org.example.Controladores;

import org.example.Excepciones.DatoNoValido;
import org.example.Modelo.Equipo;
import org.example.Modelo.EquipoDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Modelo.EquipoDAO.listaEquipos;

public class EquipoController {
    public EquipoController(EquipoDAO equipoDAO) {
    }


    public Equipo solicitarValidarDatos() {
        // Copia de la versión anterior.
        String idEquipo = solicitarDato("ID", "Teclea el ID del equipo", "^[A-Z]{1}[0-9]{3}$");
        String nombre = solicitarDato("Nombre","Teclea el nombre del equipo","^[0-9a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\\\s]{3,15}$");
        LocalDate fechaFund = solicitarFecha();

        Equipo e = new Equipo(idEquipo,fechaFund,nombre);
        return e;
    }

    public String solicitarDato(String dato, String mensaje,String expresionRegular)
    {
        String variable = "";
        boolean error;
        do
        {
            try {
                variable = JOptionPane.showInputDialog(mensaje);
                if (variable.isEmpty())
                    throw new DatoNoValido(dato + " es un campo obligatorio");
                Pattern pat = Pattern.compile(expresionRegular);
                Matcher mat = pat.matcher(variable);
                if (!mat.matches())
                    throw new DatoNoValido(dato + " no tiene un formato adecuado");
                error = false;
            } catch (DatoNoValido e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        while(error);
        return variable;
    }

    public static LocalDate solicitarFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaS = JOptionPane.showInputDialog("Ingrese la fecha de fundación: ");
        LocalDate fecha = LocalDate.parse(fechaS, formatter);
        return fecha;
    }

    public void altaEquipo() {
        try{
            Equipo equipo= solicitarValidarDatos();
            EquipoDAO.agregarEquipo(equipo);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al insertar el equipo");
        }
    }

    public void bajaEquipo() {
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

    }
}