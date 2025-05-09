SET SERVEROUTPUT ON SIZE 1000000;
/*Este procediemiento sirve para ver toda la informacion necesaria acerca de los enfrentamientos de la ultima jornada este procedimiento se utiliza en la vista VentanaResultados y
en el codigo se ejecuta en la clase EnfrentamientoDao en la linea 69 */
DECLARE
    mi_cursor SYS_REFCURSOR;
    mensaje VARCHAR2(4000);
    v_id NUMBER;
    v_hora VARCHAR2(20);
    v_fecha VARCHAR2(20);
    v_atacante VARCHAR2(100);
    v_defensor VARCHAR2(100);
    v_ganador VARCHAR2(100);
BEGIN
    -- Llamamos al procedimiento
    mostrar_enfrentamientos_ultima(mi_cursor, mensaje);

    -- Mostramos el mensaje
    DBMS_OUTPUT.PUT_LINE(mensaje);

    -- Si hay datos, los mostramos
    IF mensaje LIKE 'Enfrentamientos%' THEN
        LOOP
            FETCH mi_cursor INTO v_id, v_hora, v_fecha, v_atacante, v_defensor, v_ganador;
            EXIT WHEN mi_cursor%NOTFOUND;

            DBMS_OUTPUT.PUT_LINE(
                    'ID: ' || v_id ||
                    ', Hora: ' || v_hora ||
                    ', Fecha: ' || v_fecha ||
                    ', Atacante: ' || v_atacante ||
                    ', Defensor: ' || v_defensor ||
                    ', Ganador: ' || v_ganador
            );
        END LOOP;
    END IF;

    -- Cerramos el cursor
    CLOSE mi_cursor;
END;

/*Este otro procedimiento nos sirve para ver los datos de todos los equipos  y usa en la vista  VentanaEquipos y en el codigo se ejecuta en EquipoDao*/

DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre VARCHAR2(100);
    v_fecha DATE;
    v_jugadores NUMBER;
    v_max NUMBER;
    v_min NUMBER;
    v_prom NUMBER;
BEGIN
    sp_informe_equipos_cursor(v_cursor);
    
    DBMS_OUTPUT.PUT_LINE('Listado de equipos:');
    
    LOOP
        FETCH v_cursor INTO v_nombre, v_fecha, v_jugadores, v_max, v_min, v_prom;
        EXIT WHEN v_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(
            'Equipo: ' || v_nombre || 
            ', Fundación: ' || TO_CHAR(v_fecha, 'DD/MM/YYYY') ||
            ', Jugadores: ' || v_jugadores ||
            ', Sueldo max: ' || v_max ||
            ', min: ' || v_min ||
            ', prom: ' || ROUND(v_prom, 2)
        );
    END LOOP;
    
    CLOSE v_cursor;
END;
/

/* Y nuestro ultimo procedimiento sirve para ver la toda la informacion de un equipo concreto, importante si se prueba a manualmente cambiar el campo donde pone el nombre de toros del norte 
yo lo he puesto de prueba pero en ese campo hay que poner el equipo del que se quiera ver la informacion este procedimiento lo podemos ver desde la vista VentanaJugador el cual tiene un menu desplegable el
cual carga todos los equios y tu puedes selecionar el que tu quieras ver y en el codigo se ejecuta en la clase JugadorDao
*/


DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre_jugador VARCHAR2(100);
    v_apellido VARCHAR2(100);
    v_sueldo NUMBER;
    v_equipo_buscar VARCHAR2(100) := 'Toros del Norte'; -- Cambia esto por el equipo que quieres consultar
BEGIN
    -- Llamar al procedimiento
    obtener_jugadores_equipo(v_equipo_buscar, v_cursor);
    
    DBMS_OUTPUT.PUT_LINE('Jugadores del equipo: ' || v_equipo_buscar);
    DBMS_OUTPUT.PUT_LINE('----------------------------------');
    
    -- Recorrer el cursor y mostrar resultados
    LOOP
        FETCH v_cursor INTO v_nombre_jugador, v_apellido, v_sueldo;
        EXIT WHEN v_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(
            'Nombre: ' || v_nombre_jugador || ' ' || v_apellido ||
            ', Sueldo: ' || v_sueldo
        );
    END LOOP;
    
    -- Cerrar el cursor
    CLOSE v_cursor;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        IF v_cursor%ISOPEN THEN
            CLOSE v_cursor;
        END IF;
END;
/