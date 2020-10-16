package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/alumnos")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Alumno> getAll(){
        return alumnoService.getAll();
    }

    @PostMapping(value = "/alumnos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody Alumno alumno) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("dni", alumnoService.saveAlumno(alumno).getDni());
        return  mapResponse;

    }
}
