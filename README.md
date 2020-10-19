# Education Server
Java - Springboot Application - CRUD Server application

 - Localhost - 8888
 - Database - H2
 - Init SQL Data - /resources/data.sql
 
#### Assumptions
- Profesor can dictate one or more Cursos but a Curso can be dictated by a single Profesor.

# 1. Endpoints
 
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
# 2. JSON Models
Modelos en JSON a modo de ejemplo para poder visualizar y realizar consultas utilizando los Endpoints
### Alumno
```
{
    "dni": "39843212",
    "dni_tipo": "DNI|LE|LC|CI",
    "nombre": "Nicolas",
    "apellido": "Andreani",
    "fecha_de_nacimiento": "08-03-1993",
    "domicilio": "Rivadavia 540",
    "telefono": "2664313212",
    "sexo": "MASCULINO|FEMENINO|OTRO",
    "cursos_id": []
}
```

### Profesor
```
{
    "dni": "38190902",
    "dni_tipo": "DNI|LE|LC|CI",
    "nombre": "Alejandra",
    "apellido": "Pinto",
    "fecha_de_nacimiento": "08-01-1976",
    "domicilio": "Rivadavia 540",
    "telefono": "2664138132",
    "sexo": "MASCULINO|FEMENINO|OTRO",
    "cursos_id": [],
    "capacitaciones": [
        {
            "year": 1998,
            "institucion": "Institucion N°2",
            "title": "Capacitacion N°2",
            "descripcion": "Capacitacion de Profesor 2"
        }
    ],
    "titulos": [
        {
            "year": 1996,
            "institucion": "Institucion N°2",
            "title": "Titulo N°2"
        }
    ]
}
```
### Curso
```
{
    "nombre": "Curso N°1",
    "descripcion": "Descripcion de curso 1",
    "cant_horas": 8,
    "nota_aprobacion": 10.0,
    "alumnos_dni": ["39381301", "39381300"],
    "profesor_dni": "38190901"
}
```
### Nota
```
{
    "nota": 10.0,
    "curso_id": 1,
    "alumno_dni": "39381300"
}
```


