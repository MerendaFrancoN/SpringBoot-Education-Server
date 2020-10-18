--Create Profesores with capacitaciones and titulos
insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (1,'Rodriguez', '38190901', 'DNI', 'Rivadavia 540', '1976-01-8', 'Manuel', 'MASCULINO', '2664913821', 'Profesor');
insert into capacitaciones(id, descripcion, institucion, profesor_id, title, year) values
    (1, 'Capacitacion de Profesor 1', 'Institucion N°1', 1, 'Capacitacion N°1', 1998);
insert into titulos (id, institucion, profesor_id, title, year) values
    (1, 'Institucion N°1', 1, 'Titulo N°1', 1996);

insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (2,'Pinto', '38190902', 'DNI', 'Rivadavia 540', '1976-01-8', 'Alejandra', 'FEMENINO', '2664138132', 'Profesor');
insert into capacitaciones(id, descripcion, institucion, profesor_id, title, year) values
    (2, 'Capacitacion de Profesor 2', 'Institucion N°2', 2, 'Capacitacion N°2', 1998);
insert into titulos (id, institucion, profesor_id, title, year) values
    (2, 'Institucion N°2', 2, 'Titulo N°2', 1996);

--Create Cursos
insert into cursos (id, cant_horas, descripcion, dictado_por_id, nombre, nota_aprobacion) values
            (1, 8, 'Descripcion de curso 1', 1, 'Curso N°1', 10.0);
insert into cursos (id, cant_horas, descripcion, dictado_por_id, nombre, nota_aprobacion) values
            (2, 8, 'Descripcion de curso 2', 1, 'Curso N°2', 9.0);
insert into cursos (id, cant_horas, descripcion, dictado_por_id, nombre, nota_aprobacion) values
            (3, 8, 'Descripcion de curso 3', 1, 'Curso N°3', 8.0);
insert into cursos (id, cant_horas, descripcion, dictado_por_id, nombre, nota_aprobacion) values
            (4, 8, 'Descripcion de curso 4', 2, 'Curso N°4', 7.0);
insert into cursos (id, cant_horas, descripcion, dictado_por_id, nombre, nota_aprobacion) values
            (5, 8, 'Descripcion de curso 5', 2, 'Curso N°5', 6.0);

--Create Alumnos for cursos
insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (3,'Merenda', '39381300', 'DNI', 'Ejercito de los andes 569', '1996-03-8', 'Franco', 'MASCULINO', '2604339838', 'Alumno');
insert into cursos_alumnos (alumno_id, curso_id) values (3, 1); -- associate alumno with curso id
insert into cursos_alumnos (alumno_id, curso_id) values (3, 2);
insert into cursos_alumnos (alumno_id, curso_id) values (3, 3);
insert into cursos_alumnos (alumno_id, curso_id) values (3, 4);
insert into cursos_alumnos (alumno_id, curso_id) values (3, 5);

insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (4,'Mendiondo', '39381301', 'DNI', 'Almte. Brown 1257', '1998-01-8', 'Nicolas', 'MASCULINO', '2664398382', 'Alumno');
insert into cursos_alumnos (alumno_id, curso_id) values (4, 1); -- associate alumno with curso id
insert into cursos_alumnos (alumno_id, curso_id) values (4, 2);
insert into cursos_alumnos (alumno_id, curso_id) values (4, 3);

insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (5,'Nicola', '39381302', 'DNI', 'Av. España 392', '1993-02-8', 'Fernando', 'MASCULINO', '26643982832', 'Alumno');
insert into cursos_alumnos (alumno_id, curso_id) values (5, 4); -- associate alumno with curso id
insert into cursos_alumnos (alumno_id, curso_id) values (5, 5);

insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (6,'Tarantino', '39381303', 'DNI', 'Av. Centenario 292', '1991-02-8', 'Carolina', 'FEMENINO', '26643827312', 'Alumno');
insert into cursos_alumnos (alumno_id, curso_id) values (6, 4); -- associate alumno with curso id
insert into cursos_alumnos (alumno_id, curso_id) values (6, 5);

insert into persona (id, apellido, dni, dni_tipo, domicilio, fecha_de_nacimiento, nombre, sexo, telefono, dtype) values
    (7,'Aranza', '39381304', 'DNI', 'Av. Centenario 938', '1990-02-8', 'Micaela', 'FEMENINO', '2664987821', 'Alumno');
insert into cursos_alumnos (alumno_id, curso_id) values (7, 3); -- associate alumno with curso id
insert into cursos_alumnos (alumno_id, curso_id) values (7, 4);
insert into cursos_alumnos (alumno_id, curso_id) values (7, 5);

--Create Notas
--Alumno 3
insert into notas (id, alumno_id, curso_id, nota) values (1, 3, 1, 10.0);
insert into notas (id, alumno_id, curso_id, nota) values (2, 3, 2, 8.0);
insert into notas (id, alumno_id, curso_id, nota) values (3, 3, 3, 8.0);
insert into notas (id, alumno_id, curso_id, nota) values (4, 3, 4, 6.5);
insert into notas (id, alumno_id, curso_id, nota) values (5, 3, 5, 6.0);
--Alumno 4
insert into notas (id, alumno_id, curso_id, nota) values (6, 4, 1, 7.0);
insert into notas (id, alumno_id, curso_id, nota) values (7, 4, 2, 9.5);
insert into notas (id, alumno_id, curso_id, nota) values (8, 4, 3, 7.5);
--Alumno 5
insert into notas (id, alumno_id, curso_id, nota) values (9,  5, 4, 6.5);
insert into notas (id, alumno_id, curso_id, nota) values (10, 5, 5, 8.5);
--Alumno 6
insert into notas (id, alumno_id, curso_id, nota) values (11, 6, 4, 7.5);
insert into notas (id, alumno_id, curso_id, nota) values (12, 6, 5, 5.5);
--Alumno 7
insert into notas (id, alumno_id, curso_id, nota) values (13, 7, 3, 7.5);
insert into notas (id, alumno_id, curso_id, nota) values (14, 7, 4, 5.5);
insert into notas (id, alumno_id, curso_id, nota) values (15, 7, 5, 6.5);






