package org.example.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase BD que gestiona la conexión a la base de datos Oracle.
 */
public class BD{


    /*public static String URL = "jdbc:oracle:thin:@//ccsatserv.dnsdojo.com:1521/xepdb1";
    private static final String USER = "equipoHiber";
    private static final String PASSWORD = "Jm12345";*/


    private static final String USER = "eqdaw05";
    private static final String PASSWORD = "eqdaw05";
    public static String URL = "jdbc:oracle:thin:"+USER+"/"+PASSWORD+"@172.20.225.114:1521:orcl";

    public static Connection getConnection(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Conexión exitosa a Oracle!");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
        }
        return conn;
    }

}
