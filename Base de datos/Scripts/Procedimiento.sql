CREATE OR REPLACE PROCEDURE sp_informe_equipos_cursor (
    p_resultado OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_resultado FOR
        SELECT 
            e.Nombre AS nombre_equipo,
            e.Fecha_fund AS fecha_fundacion,
            COUNT(j.id_jugador) AS cantidad_jugadores,
            MAX(j.Sueldo) AS sueldo_max,
            MIN(j.Sueldo) AS sueldo_min,
            ROUND(AVG(j.Sueldo), 2) AS sueldo_promedio
        FROM Equipos e
        LEFT JOIN Jugadores j ON e.id_equipo = j.id_equipo
        GROUP BY e.id_equipo, e.nombre, e.fecha_fund;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20101, 'Error al generar informe con cursor: ' || SQLERRM);
END;
