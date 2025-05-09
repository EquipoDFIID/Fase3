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






