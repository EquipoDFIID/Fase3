package org.example.Controladores;

import org.example.Modelo.Campeonato;
import org.example.Modelo.CampeonatoDao;

import javax.swing.*;
import java.time.LocalDate;

public class CampeonatoController {
    public CampeonatoController(CampeonatoDao campeonatoDao) {
    }

    public void generarCampeonato() {
        boolean continuar = false;
        do {
            try {
                String id = JOptionPane.showInputDialog("Ingrese la ID del campeonato:");
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del campeonato:");
                LocalDate fechaInicio = LocalDate.now(); // Puedes modificarlo según necesidad

                String[] estadosValidos = {"Inscripción", "Curso", "Finalizado"};

                String estadoSeleccionado = (String) JOptionPane.showInputDialog(
                        null, "Seleccione el estado del campeonato:",
                        "Estado", JOptionPane.QUESTION_MESSAGE,
                        null, estadosValidos, estadosValidos[0]);

                Campeonato campeonato = new Campeonato(id, nombre, fechaInicio, estadoSeleccionado);
                CampeonatoDao.agregarCampeonato(campeonato);
                JOptionPane.showMessageDialog(null, "Campeonato creado:\n" +
                        "ID: " + campeonato.getID() + "\n" +
                        "Nombre: " + campeonato.getNombre() + "\n" +
                        "Fecha de inicio: " + campeonato.getFecha_inicio() + "\n" +
                        "Estado: " + campeonato.getEstado());

                continuar = true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al generar el campeonato: " + e.getMessage());
            }
        } while (!continuar);
    }
}
