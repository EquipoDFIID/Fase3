
CREATE OR REPLACE PROCEDURE obtener_jugadores_equipo (
    p_nombre_equipo IN EQUIPOS.NOMBRE%TYPE,
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT j.NOMBRE, j.APELLIDO, j.SUELDO
        FROM JUGADORES j
        JOIN EQUIPOS e ON j.ID_EQUIPO = e.ID_EQUIPO
        WHERE e.NOMBRE = p_nombre_equipo;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, SQLERRM);
END;

SET SERVEROUTPUT ON;

DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre  JUGADORES.NOMBRE%TYPE;
    v_apellido JUGADORES.APELLIDO%TYPE;
    v_sueldo JUGADORES.SUELDO%TYPE;
BEGIN
    obtener_jugadores_equipo('Leones Unidos', v_cursor);

    LOOP
        FETCH v_cursor INTO v_nombre, v_apellido, v_sueldo;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_nombre || ' ' || v_apellido || ' - '  || v_sueldo);
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


CREATE OR REPLACE PROCEDURE obtener_informe_equipos (
    p_resultado OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_resultado FOR
        SELECT 
            e.NOMBRE AS nombre_equipo,
            e.FECHA_FUND,
            COUNT(j.ID_JUGADOR) AS cantidad_jugadores,
            MAX(j.SUELDO) AS sueldo_maximo,
            MIN(j.SUELDO) AS sueldo_minimo,
            ROUND(AVG(j.SUELDO), 2) AS sueldo_promedio
        FROM EQUIPOS e
        LEFT JOIN JUGADORES j ON e.ID_EQUIPO = j.ID_EQUIPO
        GROUP BY e.NOMBRE, e.FECHA_FUND;

EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, SQLERRM);
END;

desc equipos

SET SERVEROUTPUT ON;
VAR cursor_resultado REFCURSOR;

BEGIN
    obtener_informe_equipos(:cursor_resultado);
END;
/

-- Ahora muestra el contenido del cursor
PRINT cursor_resultado;


    
select * from equipos;