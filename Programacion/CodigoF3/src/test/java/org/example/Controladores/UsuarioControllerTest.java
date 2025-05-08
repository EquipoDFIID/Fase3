package org.example.Controladores;

import org.example.Modelo.Usuario;
import org.example.Modelo.UsuarioDAO;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTest {

    private static UsuarioDAO usuarioDAO;
    private static UsuarioController usuarioController;

    @BeforeAll
    static void setUp() {
        usuarioDAO = new UsuarioDAO();
        usuarioController = new UsuarioController(usuarioDAO);
    }

    @Test
    @Order(1)
    void testCrearCuenta() {
        usuarioController.crearCuenta("tester", "Test", "1234");
        Usuario usuario = usuarioDAO.selectUsuarioNick("tester", "1234");
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNombre());
        assertEquals("user", usuario.getTipoUsuario());
    }

    @Test
    @Order(2)
    void testSelectUsuarioNick() {
        Usuario usuario = usuarioController.selectUsuarioNick("tester", "1234");
        assertNotNull(usuario);
        assertEquals("Test", usuario.getNombre());
    }

    @Test
    @Order(3)
    void testSelectUsuarioNom() { //Este tiene que dar error porque es otro nickname
        Usuario usuario = usuarioController.selectUsuarioNom("Test", "1234");
        assertNotNull(usuario);
        assertEquals("tester2", usuario.getNickname());
    }

    @Test
    @Order(4)
    void testComprobarNicknameTrue() {
        assertTrue(usuarioController.comprobarNickname("tester"));
    }

    @Test
    @Order(5)
    void testComprobarNicknameFalse() {
        assertFalse(usuarioController.comprobarNickname("tester2"));
    }
}
