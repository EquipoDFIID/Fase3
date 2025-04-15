--Vista para ver los datos de los jugadores:
CREATE OR REPLACE VIEW vista_jugadores AS
    SELECT 
    j.NOMBRE, j.NACIONALIDAD, j.FECHA_NAC AS "FECHA DE NACIMIENTO",
    j.NICKNAME, e.NOMBRE 
    AS "NOMBRE DE EQUIPO"
    FROM JUGADORES j
    JOIN EQUIPOS e ON j.ID_EQUIPO = e.ID_EQUIPO;

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

select * from vista_usuarios;
