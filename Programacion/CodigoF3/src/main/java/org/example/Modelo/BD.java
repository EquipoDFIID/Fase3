package org.example.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD{


    private static final String URL = "jdbc:oracle:thin:@//ccsatserv.dnsdojo.com:1521/xepdb1";
    private static final String USER = "equipoHiber";
    private static final String PASSWORD = "Jm12345";

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
