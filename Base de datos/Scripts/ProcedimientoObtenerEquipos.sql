CREATE OR REPLACE PROCEDURE obtener_informe_equipos (
    p_resultado OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN p_resultado FOR
        SELECT 
            e.NOMBRE AS "Nombre de equipo",
            e.FECHA_FUND AS "Fecha de fundación",
            COUNT(j.ID_JUGADOR) AS "Cantidad de jugadores",
            NVL(MAX(j.SUELDO), 0) AS "Sueldo máximo",
            NVL(MIN(j.SUELDO), 0) AS "Sueldo mínimo",
            NVL(ROUND(AVG(j.SUELDO), 2), 0) AS "Sueldo promedio"
        FROM EQUIPOS e
        LEFT JOIN JUGADORES j ON e.ID_EQUIPO = j.ID_EQUIPO
        GROUP BY e.ID_EQUIPO, e.NOMBRE, e.FECHA_FUND;

EXCEPTION
    WHEN OTHERS THEN
    
    RAISE_APPLICATION_ERROR(-20001, 'Error al obtener informe de equipos: ' || SQLERRM);
END;
 
