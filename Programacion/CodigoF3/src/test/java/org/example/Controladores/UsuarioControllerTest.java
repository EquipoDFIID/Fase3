package org.example.Controladores;

import org.example.Modelo.Usuario;
import org.example.Modelo.UsuarioDAO;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    private static UsuarioDAO usuarioDAO;
    private static UsuarioController usuarioController;

    @BeforeAll
    static void setUp() throws SQLException {
        limpiarUsuarioTest();
        usuarioDAO = new UsuarioDAO();
        usuarioController = new UsuarioController(usuarioDAO);
    }

    private static void limpiarUsuarioTest() throws SQLException {
        Connection con = org.example.Modelo.BD.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM USUARIOS WHERE lower(NICKNAME)='tester'");
        st.executeUpdate("DELETE FROM USUARIOS WHERE lower(NICKNAME)='usuarioInexistente123'");
    }

    @Test
    void testCrearCuenta() throws Exception {
        usuarioController.crearCuenta("tester", "Test", "1234");
        Usuario usuario = usuarioDAO.selectUsuarioNick("tester", "1234");
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNombre());
        assertEquals("user", usuario.getTipoUsuario());
    }

    @Test
    void testSelectUsuarioNick() throws Exception {
        usuarioController.crearCuenta("tester", "Test", "1234");
        Usuario usuario = usuarioController.selectUsuarioNick("tester", "1234");
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNickname());
    }

   @Test
    void testSelectUsuarioNom() throws Exception {
        usuarioController.crearCuenta("tester", "Test", "1234");
        Usuario usuario = usuarioController.selectUsuarioNom("Test", "1234");
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNombre());
    }

    @Test
    void testComprobarNicknameTrue() throws Exception {
        usuarioController.crearCuenta("tester", "Test", "1234");
        assertTrue(usuarioController.comprobarNickname("tester"));
    }

    @Test
    void testComprobarNicknameFalse() throws Exception {
        assertFalse(usuarioController.comprobarNickname("usuarioInexistente123"));
    }
}
