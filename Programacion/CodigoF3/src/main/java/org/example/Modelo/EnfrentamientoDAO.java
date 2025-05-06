package org.example.Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
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

}