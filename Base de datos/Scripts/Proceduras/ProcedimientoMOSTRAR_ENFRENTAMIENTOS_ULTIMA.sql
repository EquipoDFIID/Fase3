create PROCEDURE mostrar_enfrentamientos_ultima (
    p_cursor OUT SYS_REFCURSOR,
    p_mensaje OUT VARCHAR2
) AS
    v_id_jornada JORNADAS.ID_JORNADA%TYPE;
BEGIN
    -- Buscar la última jornada con al menos un ganador confirmado
    SELECT MAX(E.ID_JORNADA)
    INTO v_id_jornada
    FROM ENFRENTAMIENTOS E
             JOIN EQUIPOS G ON E.EQUIPO_GANADOR = G.ID_EQUIPO
    WHERE G.NOMBRE <> 'Pendiente';

    IF v_id_jornada IS NULL THEN
        p_mensaje := 'No hay jornadas con enfrentamientos confirmados.';
        OPEN p_cursor FOR SELECT * FROM DUAL WHERE 1=0; -- cursor vacío
    ELSE
        p_mensaje := 'Enfrentamientos de la última jornada con resultados confirmados:';

        OPEN p_cursor FOR
            SELECT
                E.ID_ENFRENTAMIENTO,
                TO_CHAR(E.HORA, 'HH24:MI:SS') AS HORA,
                TO_CHAR(E.FECHA_ENF, 'DD/MM/YYYY') AS FECHA_ENF,
                A.NOMBRE AS NOMBRE_ATACANTE,
                D.NOMBRE AS NOMBRE_DEFENSOR,
                G.NOMBRE AS NOMBRE_GANADOR
            FROM ENFRENTAMIENTOS E
                     JOIN EQUIPOS A ON E.EQUIPO_ATACANTE = A.ID_EQUIPO
                     JOIN EQUIPOS D ON E.EQUIPO_DEFENSOR = D.ID_EQUIPO
                     JOIN EQUIPOS G ON E.EQUIPO_GANADOR = G.ID_EQUIPO
            WHERE E.ID_JORNADA = v_id_jornada;
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_mensaje := 'Error al recuperar enfrentamientos: ' || SQLERRM;
        OPEN p_cursor FOR SELECT * FROM DUAL WHERE 1=0;
END;
/