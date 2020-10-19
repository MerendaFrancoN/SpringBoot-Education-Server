# Education Server
Java - Springboot Application - CRUD Server application

 - Localhost - 8888
 - Database - H2
 - Init SQL Data - /resources/data.sql
 
## Assumptions
- Profesor can dictate one or more Cursos but a Curso can be dictated by a single Profesor.
 
# Endpoints

### Alumnos
- GET /alumnos -> Get all alumnos
- GET /alumnos/get_cursos?{dni} -> Get Cursos attended by Alumno
- GET /alumnos/get_cursos_aprobados?{dni} -> Get Cursos Aprobados of an Alumno
- GET /alumnos/get_aprobados?{curso_id} -> Get Alumnos Aprobados of a Curso
- POST /alumnos -> Create Alumno
- PUT /alumnos -> Update Alumno
- DELETE /alumnos/{dni} -> Delete Alumno with DNI

### Profesores
- GET /profesores -> Get all Profesores
- GET /profesores/get_cursos?{dni} -> Get Cursos of a Profesor
- POST /profesores -> Create Profesor
- DELETE /profesores/{dni} -> Delete Profesor with DNI
- PUT /profesores -> Update Profesor

### Notas
- GET /notas -> Get all Notas
- POST /notas -> Create Nota
- PUT /notas -> Update Nota
- DELETE /notas/{id} -> Delete Nota with ID

### Cursos
- GET /cursos -> Get all cursos
- GET /cursos/get_alumnos?{curso_id} -> Get alumnos of a given Curso
- POST /cursos -> Create Curso
- PUT /cursos -> Update Curso
- DELETE /cursos/{id} -> Delete Curso with ID

