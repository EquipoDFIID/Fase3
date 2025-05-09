DROP TABLE COMPETICIONES CASCADE CONSTRAINTS;
CREATE TABLE COMPETICIONES (
    ID_COMPETICION NUMBER(4)GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR2(20),
    ESTADO VARCHAR(20) CONSTRAINT com_est_ck CHECK(ESTADO IN('inscripcion', 'en curso', 'finalizada'))
);

DROP TABLE JORNADAS CASCADE CONSTRAINTS;
CREATE TABLE JORNADAS (
    ID_JORNADA NUMBER(4) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FECHA DATE,
    ID_COMPETICION NUMBER(4),
    CONSTRAINT jor_com_fk FOREIGN KEY (ID_COMPETICION) REFERENCES COMPETICIONES(ID_COMPETICION)
    ON DELETE CASCADE
);

DROP TABLE EQUIPOS CASCADE CONSTRAINTS;
CREATE TABLE EQUIPOS (
    ID_EQUIPO NUMBER(4) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR2(20)NOT NULL UNIQUE,
    FECHA_FUND DATE
);

DROP TABLE JUGADORES CASCADE CONSTRAINTS;
CREATE TABLE JUGADORES (
    ID_JUGADOR NUMBER(4) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR2(20)NOT NULL,
    APELLIDO VARCHAR2(30),
    NACIONALIDAD VARCHAR2(30),
    FECHA_NAC DATE,
    NICKNAME VARCHAR2(20) UNIQUE,
    SUELDO NUMBER(5),
    ID_EQUIPO NUMBER(4),
    CONSTRAINT jug_equ_fk FOREIGN KEY(ID_EQUIPO) REFERENCES EQUIPOS(ID_EQUIPO)
    ON DELETE CASCADE
);

DROP TABLE ENFRENTAMIENTOS CASCADE CONSTRAINTS;
CREATE TABLE ENFRENTAMIENTOS (
    ID_ENFRENTAMIENTO NUMBER(4) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    HORA DATE NOT NULL,
    FECHA_ENF DATE NOT NULL,
    EQUIPO_ATACANTE NUMBER(4),
    EQUIPO_DEFENSOR NUMBER(4),
    EQUIPO_GANADOR NUMBER(4),
    ID_JORNADA NUMBER(4),
    CONSTRAINT enf_atc_fk FOREIGN KEY (EQUIPO_ATACANTE) REFERENCES EQUIPOS(ID_EQUIPO),
    CONSTRAINT enf_def_fk FOREIGN KEY (EQUIPO_DEFENSOR) REFERENCES EQUIPOS(ID_EQUIPO),
    CONSTRAINT enf_gan_fk FOREIGN KEY (EQUIPO_GANADOR) REFERENCES EQUIPOS(ID_EQUIPO),
    CONSTRAINT enf_jor_fk FOREIGN KEY (ID_JORNADA) REFERENCES JORNADAS(ID_JORNADA)
);

DROP TABLE USUARIOS CASCADE CONSTRAINTS;
CREATE TABLE USUARIOS (
    ID_USUARIO NUMBER(4) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR2(30) NOT NULL,
    NICKNAME VARCHAR2(30) NOT NULL UNIQUE,
    CLAVE VARCHAR2(4) NOT NULL, 
    TIPO_USUARIO VARCHAR2(30) NOT NULL,
    CONSTRAINT CHK_TIPO_USUARIO CHECK (TIPO_USUARIO IN ('admin', 'user'))
);

create PROCEDURE mostrar_enfrentamientos_ultima (
    p_cursor OUT SYS_REFCURSOR,
    p_mensaje OUT VARCHAR2
) AS
    v_id_jornada JORNADAS.ID_JORNADA%TYPE;
    v_fecha_jornada JORNADAS.FECHA%TYPE;
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
        -- Obtener la fecha de la jornada
        SELECT FECHA
        INTO v_fecha_jornada
        FROM JORNADAS
        WHERE ID_JORNADA = v_id_jornada;

        -- Crear el mensaje con ID y Fecha de jornada
        p_mensaje := 'Enfrentamientos de la última jornada con resultados confirmados (ID: ' ||
                     v_id_jornada || ', Fecha: ' || TO_CHAR(v_fecha_jornada, 'DD/MM/YYYY') || '):';

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


create OR REPLACE PROCEDURE obtener_jugadores_equipo (
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


create OR REPLACE PROCEDURE sp_informe_equipos_cursor (
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
/


--1.Trigger máximo 6 jugadores:
drop trigger TRG_LIM_JUG;

CREATE OR REPLACE TRIGGER trg_limite_jugadores
    FOR INSERT OR UPDATE ON jugadores
    COMPOUND TRIGGER

    v_num_jugadores NUMBER;
    v_max CONSTANT NUMBER := 6;

AFTER STATEMENT IS
BEGIN
    -- Recorremos los equipos únicos que existen actualmente en la tabla
    FOR equipos IN (
        SELECT id_equipo
        FROM jugadores
        GROUP BY id_equipo
    ) LOOP
        -- Contamos la cantidad de jugadores en cada equipo
        SELECT COUNT(*) INTO v_num_jugadores
        FROM jugadores
        WHERE id_equipo = equipos.id_equipo;

        -- Si supera el máximo permitido, lanzamos un error
        IF v_num_jugadores > v_max THEN
            RAISE_APPLICATION_ERROR(-20002,
                'El equipo ' || equipos.id_equipo || 
                ' tiene ' || v_num_jugadores || 
                ' jugadores. El máximo permitido es ' || v_max || '.');
        END IF;
    END LOOP;
END AFTER STATEMENT;

END trg_limite_jugadores;
/
--2.Trigger para que haya mínimo 2 jugadores en un equipo en una competición:
drop trigger TRG_VAL_EQU_PARA_COMP;
CREATE OR REPLACE TRIGGER TRG_VAL_EQU_PARA_COMP
BEFORE INSERT ON COMPETICIONES
DECLARE
v_equipos_invalidos NUMBER;
v_minimo_jugadores NUMBER := 2;
v_minimo_jug_exception EXCEPTION;

BEGIN

SELECT COUNT(*)
INTO v_equipos_invalidos
FROM EQUIPOS e
WHERE (
SELECT COUNT(*)
FROM JUGADORES j
WHERE j.ID_EQUIPO = e.ID_EQUIPO
) < v_minimo_jugadores;

    IF v_equipos_invalidos > 0 THEN
    RAISE v_minimo_jug_exception;
    END IF;

EXCEPTION
WHEN v_minimo_jug_exception THEN
RAISE_APPLICATION_ERROR(-20011,
'No se puede crear la competición: ' || v_equipos_invalidos ||
' equipos tienen menos de ' || v_minimo_jugadores || ' jugadores');
END;

--3. Trigger que no permite modificaciones fuera de la etapa de inscripción, en Jugadores:
drop trigger TRG_BLOQ_MOD_JUGA;
CREATE OR REPLACE TRIGGER TRG_BLOQ_MOD_JUGA
BEFORE INSERT OR UPDATE OR DELETE ON jugadores
FOR EACH ROW
DECLARE
    v_existe_calendario NUMBER;
BEGIN
-- Verificar si existe al menos una competición cuyo estado no sea 'INSCRIPCION'
    SELECT COUNT(*) INTO v_existe_calendario
    FROM competiciones
    WHERE Estado != 'inscripcion';

    IF v_existe_calendario > 0 THEN
        RAISE_APPLICATION_ERROR(-20005,
'No se permiten modificaciones a jugadores una vez generado el calendario de competición');
    END IF;
END;
--4. Trigger que no permite modificaciones fuera de la etapa de inscripción, en Equipos:
drop trigger TRG_BLOQ_MOD_EQUIPO;
CREATE OR REPLACE TRIGGER TRG_BLOQ_MOD_EQUIPO
BEFORE INSERT OR UPDATE OR DELETE ON Equipos
FOR EACH ROW
DECLARE
    v_existe_calendario NUMBER;
BEGIN
    -- Verificar si existe al menos una competición cuyo estado no sea 'inscripcion'
    SELECT COUNT(*) INTO v_existe_calendario
    FROM Competiciones
    WHERE Estado != 'inscripcion';

    IF v_existe_calendario > 0 THEN
        RAISE_APPLICATION_ERROR(-20006,
        'No se permiten modificaciones en equipos una vez generado 
        el calendario de competición');
    END IF;
END;
--5. Trigger para que el sueldo del jugador sea mayor que el SMI:
drop trigger trg_sueldo_jugador;
CREATE OR REPLACE TRIGGER trg_sueldo_jugador
BEFORE INSERT OR UPDATE OF sueldo ON jugadores
FOR EACH ROW
BEGIN
    IF :NEW.Sueldo < 1184 THEN
RAISE_APPLICATION_ERROR(-20004, 'El sueldo del jugador debe ser mayor a 1184.');
    END IF;
END;


--6. Trigger para comprobar que haya un número par de equipos, una vez que el estado de la competición sea "en curso":
DROP TRIGGER TRG_VAL_EQU_PARA_COMP;
create trigger TRG_VAL_EQU_PARA_COMP
    before insert
    on COMPETICIONES
DECLARE
v_equipos_invalidos NUMBER;
v_minimo_jugadores NUMBER := 2;
v_minimo_jug_exception EXCEPTION;

BEGIN

SELECT COUNT(*)
INTO v_equipos_invalidos
FROM EQUIPOS e
WHERE (
SELECT COUNT(*)
FROM JUGADORES j
WHERE j.ID_EQUIPO = e.ID_EQUIPO
) < v_minimo_jugadores;

    IF v_equipos_invalidos > 0 THEN
    RAISE v_minimo_jug_exception;
    END IF;

EXCEPTION
WHEN v_minimo_jug_exception THEN
RAISE_APPLICATION_ERROR(-20011,
'No se puede crear la competici?n: ' || v_equipos_invalidos ||
' equipos tienen menos de ' || v_minimo_jugadores || ' jugadores');
END;



select * from vista_jugadores;
 
--Vista para ver los datos de los enfrentamientos    
CREATE OR REPLACE VIEW vista_enfrentamientos AS
SELECT 
    ea.NOMBRE AS "EQUIPO ATACANTE",
    ed.NOMBRE AS "EQUIPO DEFENSOR",
    eg.NOMBRE AS "EQUIPO GANADOR",
    e.FECHA_ENF AS "FECHA",
    e.HORA
FROM ENFRENTAMIENTOS e
LEFT JOIN EQUIPOS ea ON e.EQUIPO_ATACANTE = ea.ID_EQUIPO
LEFT JOIN EQUIPOS ed ON e.EQUIPO_DEFENSOR = ed.ID_EQUIPO
LEFT JOIN EQUIPOS eg ON e.EQUIPO_GANADOR = eg.ID_EQUIPO;

select * from vista_enfrentamientos;

--Vista para ADMIN: consulta de usuarios
CREATE OR REPLACE VIEW vista_usuarios AS
SELECT 
    ID_USUARIO,
    NOMBRE,
    TIPO_USUARIO
FROM USUARIOS;



