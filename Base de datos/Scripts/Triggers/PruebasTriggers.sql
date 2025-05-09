--1. Prueba TRG_LIMITE_JUGADORES(m�ximo 6 jugadores en el equipo):
-- Crear equipos
INSERT INTO EQUIPOS (NOMBRE, FECHA_FUND)
VALUES ('Dragones', TO_DATE('2010-03-15','YYYY-MM-DD'));
INSERT INTO EQUIPOS (NOMBRE, FECHA_FUND) 
VALUES ('Titanes', TO_DATE('2012-07-10','YYYY-MM-DD'));

-- Insertar 7 jugadores en el equipo 'Dragones' (ID 1)
INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Juan', 'P�rez', 'Espa�a', TO_DATE('1995-04-10','YYYY-MM-DD'), 'JP10', 2000, 1);

INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Luis', 'G�mez', 'M�xico', TO_DATE('1994-05-11','YYYY-MM-DD'), 'LG94', 2100, 1);

INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Carlos', 'Ruiz', 'Argentina', TO_DATE('1993-06-12','YYYY-MM-DD'), 'CR7', 2500, 1);

INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Andr�s', 'Lopez', 'Chile', TO_DATE('1992-07-13','YYYY-MM-DD'), 'AL13', 2200, 1);

INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Pedro', 'Martinez', 'Colombia', TO_DATE('1991-08-14','YYYY-MM-DD'), 'PM14', 2400, 1);

INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Ricardo', 'Fernandez', 'Per�', TO_DATE('1990-09-15','YYYY-MM-DD'), 'RF15', 2300, 1);
/*Al intentar insertar 7� jugador en el 1er equipo, se dispara el trigger: 
 Error: El equipo 1 ya tiene 6 jugadores. No se permiten m�s de 6 jugadores por equipo. */
INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Ric', 'Fer', 'Per�', TO_DATE('1990-09-15','YYYY-MM-DD'), 'RF16', 2300, 1);

-- Crear un jugador en el equipo 2 (Titanes)
INSERT INTO JUGADORES (NOMBRE, APELLIDO, NACIONALIDAD, FECHA_NAC, NICKNAME, SUELDO, ID_EQUIPO)
VALUES ('Luc�a', 'Reyes', 'Espa�a', TO_DATE('1996-11-17','YYYY-MM-DD'), 'LR17', 2100, 2);

-- Al intentar mover al jugador creado al equipo 1, se dispara el trigger 
UPDATE JUGADORES
SET ID_EQUIPO = 1
WHERE NICKNAME = 'LR17';


--2.Prueba para trigger TRG_VAL_EQU_PARA_COMP(min 2 jugadores):
--Se crea un nuevo juego:
INSERT INTO equipos (nombre, fecha_fund) VALUES ('MenosDeDosJug',TO_DATE('2025-04-14','YYYY-MM-DD'));
select * from equipos;
--Se inserta solo un juegador en el equipo nuevo:
INSERT INTO jugadores (nombre, id_equipo) VALUES ('Jugador_solitario',21);
INSERT INTO competiciones(nombre) VALUES('Competi');


--3.Prueba para triggers de bloqueo de modificaci�n fuera de la etapa de inscripci�n:
--se crea una competici�n es estado de inscripci�n:
INSERT INTO Competiciones (nombre, estado)
VALUES ( 'Liga Primavera', 'inscripcion');
-- Insertar equipo
INSERT INTO Equipos (Nombre, Fecha_fund)
VALUES ('Tigres FC', TO_DATE('2000-05-01', 'YYYY-MM-DD'));

-- Insertar jugador
INSERT INTO Jugadores (Nombre, Apellido, Nacionalidad, Fecha_nac, Nickname, Sueldo, id_equipo)
VALUES ('Juan', 'P�rez', 'Argentina', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 'ElCa�on', 1500, 1);
--Se modifica el estado de la competici�n:
UPDATE Competiciones
SET Estado = 'en curso'
WHERE id_competicion = 1;

-- Este intento debe lanzar un error por el trigger TRG_BLOQ_MOD_EQUIPO
UPDATE Equipos
SET Nombre = 'Tigres Renovados'
WHERE id_equipo = 1;


--4. Prueba para el sueldo del jugador trg_sueldo_jugador:
INSERT INTO jugadores(Nombre, Apellido, Nacionalidad, Fecha_nac, Nickname, Sueldo, id_equipo)
VALUES('Bryan','Alzamora','Per�',TO_DATE('1995-01-01', 'YYYY-MM-DD'),'ElBra',1000, 21);