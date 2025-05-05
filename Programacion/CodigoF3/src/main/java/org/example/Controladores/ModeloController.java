package org.example.Controladores;

import org.example.Modelo.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ModeloController {

    private CampeonatoDao campeonatoDao;
    private CampeonatoController campeonatoController;

    private EnfrentamientoController enfrentamientoController;
    private EnfrentamientoDAO enfrentamientoDao;

    private EquipoController equipoController;
    private EquipoDAO equipoDao;

    private JornadaController jornadaController;
    private JornadaDAO jornadaDao;

    private JugadorController jugadorController;
    private JugadorDAO jugadorDao;

    private UsuarioController usuarioController;
    private UsuarioDAO usuarioDao;

    private VistaController vc;

    public ModeloController() {
        try{
            //BaseDatos.abrirConexion();
            Connection con =BD.getConnection();

            campeonatoDao=new CampeonatoDao();
            campeonatoController= new CampeonatoController(campeonatoDao);

            enfrentamientoDao=new EnfrentamientoDAO();
            enfrentamientoController=new EnfrentamientoController(enfrentamientoDao);

            equipoDao=new EquipoDAO();
            equipoController=new EquipoController(equipoDao);

            jornadaDao=new JornadaDAO();
            jornadaController=new JornadaController(jornadaDao);

            jugadorDao=new JugadorDAO();
            jugadorController=new JugadorController(jugadorDao);

            usuarioDao=new UsuarioDAO();
            usuarioController=new UsuarioController(usuarioDao);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setVista(VistaController vc) {
        this.vc=vc;
    }

    public ArrayList <Equipo> selectObjetosEquipo() {
        return equipoController.selectObjetosEquipo();
    }
    public ArrayList <Jugador> selectObjetosJugador() {return jugadorController.selectObjetosJugador();
    }

    public void buscarJugador(String nombreJugador) {jugadorController.buscarJugador(nombreJugador);
    }
    public void buscarEquipo(String nombreEquipo) {equipoController.buscarEquipo(nombreEquipo);
    }

    public void altaEquipo(String nombre, String fecha){
        equipoController.altaEquipo(nombre, fecha);
    }
    public void bajaEquipo() {
        equipoController.bajaEquipo();
    }
    public void modificarEquipo(String nombre, String fecha) {
        equipoController.modificarEquipo(nombre, fecha);
    }

    public void altaJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo equipo){
         jugadorController.altaJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, equipo);
    }
    public void bajaJugador( String nombreJugador) {
        jugadorController.bajaJugador( nombreJugador);
    }
    public void modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaNacimiento, String nickname, double sueldo, Equipo ej) {
        jugadorController.modificarJugador(nombre, apellido, nacionalidad, fechaNacimiento, nickname, sueldo, ej);
    }



    public Usuario selectNombre(String nombreUsuario) {
        return usuarioController.selectNombre(nombreUsuario);
    }

    public void inscripcionCerrada() {
        int numeroEquipos = equipoController.selectCountEquipos();
        ArrayList<Equipo> equipos = equipoController.selectAllEquipos();

        // Para registrar enfrentamientos únicos: "id1-id2" donde id1 < id2
        ArrayList<String> enfrentamientosRealizados = new ArrayList<>();
        int contadorJornadas = 0;
        int contadorEnfrentamientos=0;
        while (contadorJornadas < numeroEquipos) {
            jornadaController.crearJornada();
            System.out.println("nueva jornada");
            // Copia temporal para esta jornada
            ArrayList<Equipo> disponibles = new ArrayList<>(equipos);

            for (int o = 0; o < numeroEquipos / 2; o++) {
                Equipo atacante = null;
                Equipo defensor = null;

                // Buscar una pareja que no se haya enfrentado antes
                while (contadorEnfrentamientos<numeroEquipos/2) {
                    atacante = selecionarEquipoRandom(disponibles);
                    disponibles.remove(atacante);

                    defensor = selecionarEquipoRandom(disponibles);
                    disponibles.remove(defensor);

                    // Generar clave única ordenada (por IDs o nombres)
                    String key = generarClaveUnica(atacante, defensor,enfrentamientosRealizados);

                    /*if (!enfrentamientosRealizados.contains(key)) {
                        enfrentamientosRealizados.add(key);
                        encontrado = true;
                        System.out.println("Enfrentamiento: " + key);
                    } else {
                        // Si ya se enfrentaron, devolver al pool y seguir buscando
                        disponibles.add(atacante);
                        disponibles.add(defensor);
                        atacante = null;
                        defensor = null;
                    }*/
                }

                /* Si no se encontró pareja válida, salir del bucle
                if (atacante != null && defensor != null) {
                    enfrentamientoController.crearEnfrentamiento(atacante, defensor);
                } else {
                    System.out.println("No se pudo asignar enfrentamiento sin repetir.");
                }
            }*/
                contadorJornadas++;
            }
        }
    }

    // Método para generar clave única para una pareja de equipos
    private String generarClaveUnica(Equipo e1, Equipo e2, ArrayList<String> enfrentamientosRealizados) {
        String id1 = e1.getNombre(); // o e1.getId() si tienes ID numérico
        String id2 = e2.getNombre();

        // Ordenar para que "A-B" sea igual que "B-A"
        String clave;
        if (id1.compareTo(id2) < 0) {
            clave = id1 + "-" + id2;
        } else {
            clave = id2 + "-" + id1;
        }

        // Insertar clave en la lista
        enfrentamientosRealizados.add(clave);

        return clave;
    }

    private boolean buscarEnfrentamiento(String clave, ArrayList<String> enfrentamientosRealizados) {
        boolean encontrado = false;
        for(int i=0;i<enfrentamientosRealizados.size();i++){
            if(enfrentamientosRealizados.get(i).equals(clave)){
                encontrado= true;
            }
        }
        return encontrado;
    }



    private Equipo selecionarEquipoRandom(ArrayList<Equipo> equipos) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(equipos.size());

        // Obtener y remover el equipo seleccionado para evitar repeticiones
        Equipo equipoSeleccionado = equipos.remove(indiceAleatorio);

        return equipoSeleccionado;
    }


    public void crearCuenta(String nombre, String clave) {
        usuarioController.crearCuenta(nombre, clave);
    }
}
