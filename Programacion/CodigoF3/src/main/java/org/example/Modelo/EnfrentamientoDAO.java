package org.example.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfrentamientoDAO {

    static Connection con = BD.getConnection();

    public EnfrentamientoDAO() {
    }

    public static void altaEnfrentamiento(Enfrentamiento enfrentamiento) throws Exception {
        String sql = "INSERT INTO ENFRENTAMIENTOS (HORA, FECHA_ENF, EQUIPO_ATACANTE, EQUIPO_DEFENSOR, ID_JORNADA) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTime(1, Time.valueOf(enfrentamiento.getHoraEnfrentamiento()));
            ps.setDate(2, Date.valueOf(enfrentamiento.getFechEnfrentamiento()));
            ps.setInt(3, enfrentamiento.getEquipoAtacante().getIdEquipo());
            ps.setInt(4, enfrentamiento.getEquipoDefensor().getIdEquipo());
            ps.setInt(5, enfrentamiento.getJornada().getIdJornada());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void asignarGanadorEnfrentamiento(Enfrentamiento enfrentamiento) throws Exception {
        try {
            String sql = "UPDATE enfrentamientos SET equipo_ganador = ? WHERE id_enfrentamiento = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, enfrentamiento.getEquipoGanador().getIdEquipo());
            ps.setInt(2, enfrentamiento.getIdEnfrentamiento());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Enfrentamiento> selectEnfrentamientosJornada(int idJornada) throws Exception {
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
    public static String procedimientoEnfrentamientosUltimaJornada() throws Exception {
        StringBuilder tabla = new StringBuilder();
        CallableStatement cstmt = null;

        try {
            String sql = "{ call mostrar_enfrentamientos_ultima_jornada(?, ?) }";
            cstmt = con.prepareCall(sql);

            // Registrar parámetros
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            cstmt.execute();

            // Obtener mensaje de salida
            String mensaje = cstmt.getString(2);
            tabla.append(mensaje).append("\n\n");

            // Si hay datos, procesar el cursor
            if (mensaje.startsWith("Enfrentamientos")) {
                try (ResultSet rs = (ResultSet) cstmt.getObject(1)) {
                    // Encabezados de la tabla
                    tabla.append("| ID   | HORA     | FECHA      | ATACANTE         | DEFENSOR         | GANADOR          |\n");
                    tabla.append("|------|----------|------------|------------------|------------------|------------------|\n");

                    // Procesar cada fila
                    while (rs.next()) {
                        int idEnfrentamiento = rs.getInt("ID_ENFRENTAMIENTO");
                        String hora = rs.getString("HORA");
                        String fecha = rs.getString("FECHA_ENF");
                        String atacante = rs.getString("NOMBRE_ATACANTE");
                        String defensor = rs.getString("NOMBRE_DEFENSOR");
                        String ganador = rs.getString("NOMBRE_GANADOR");

                        tabla.append(String.format("| %-4d | %-8s | %-10s | %-16s | %-16s | %-16s |\n",
                                idEnfrentamiento,
                                hora,
                                fecha,
                                atacante,
                                defensor,
                                ganador));
                    }
                }
            }

        } catch (Exception ex) {
            tabla.append("Error al generar la tabla: ").append(ex.getMessage());
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }
        System.out.println(tabla.toString());
        return tabla.toString();
    }
}