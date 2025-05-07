package org.example.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfrentamientoDAO {

    static Connection con = BD.getConnection();
    public EnfrentamientoDAO() {
    }

    public static void altaEnfrentamiento(Enfrentamiento enfrentamiento) {
        try{
            String sql = "INSERT INTO ENFRENTAMIENTOS(HORA,FECHA_ENF,EQUIPO_ATACANTE,EQUIPO_DEFENSOR,EQUIPO_GANADOR,ID_JORNADA) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTime(1, Time.valueOf(enfrentamiento.getHoraEnfrentamiento()));
            ps.setDate(2,Date.valueOf(enfrentamiento.getFechEnfrentamiento()));
            ps.setInt(3,enfrentamiento.getEquipoAtacante().getIdEquipo());
            ps.setInt(4,enfrentamiento.getEquipoDefensor().getIdEquipo());
            ps.setInt(5,enfrentamiento.getEquipoGanador().getIdEquipo());
            ps.setInt(6,enfrentamiento.getJornada().getIdJornada());
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static ArrayList<Enfrentamiento> selectAllEnfrentamientos(int idJornada) {
        ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ENFRENTAMIENTOS WHERE ID_JORNADA = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idJornada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Enfrentamiento e = new Enfrentamiento();
                e.setIdEnfrentamiento(rs.getInt("ID_ENFRENTAMIENTO"));
                e.setHoraEnfrentamiento(rs.getTime("HORA").toLocalTime());
                e.setFechEnfrentamiento(rs.getDate("FECHA_ENF").toLocalDate());
                //lo de abajo igual esta mal
                e.setEquipoAtacante(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_ATACANTE")));
                e.setEquipoDefensor(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_DEFENSOR")));
                e.setEquipoGanador(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_GANADOR")));
                e.setJornada(JornadaDAO.buscarJornada(rs.getInt("ID_JORNADA")));
                enfrentamientos.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return enfrentamientos;
    }

}