create PROCEDURE obtener_jugadores_equipo (
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
/