package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/alumnos")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AlumnoDTO> getAll(){
        return alumnoService.getAll();
    }

    @PostMapping(value = "/alumnos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody AlumnoDTO alumno) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("dni", alumnoService.saveAlumno(alumno).getDni());
        return  mapResponse;

    }

    @GetMapping(value = "/alumnos/busqueda")
    @ResponseBody
    public Object searchCursos(@RequestParam("dni") String dni) {
        AlumnoDTO alumnoDTO = alumnoService.getAlumnoByDNI(dni);
        if(alumnoDTO != null){
            return new ResponseEntity<>(alumnoDTO.getCursos_tomados(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }
}
