package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Modelo.BD;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class TestBD {

    /**
     * Test para verificar si la conexión a la base de datos es exitosa.
     */
    @Test
    void testConexionExitosa() {
        Connection conn = BD.getConnection();
        assertNotNull(conn, "La conexión debería ser exitosa y no nula.");
    }

    /**
     * Test para verificar el manejo de conexiones fallidas.
     */
    @Test
    void testConexionFallida() {
        BD.URL = "jdbc:oracle:thin:@//direccion_incorrecta:1521/xepdb1";
        Connection conn = BD.getConnection();
        assertNull(conn, "La conexión debería fallar y devolver null.");
    }

}
