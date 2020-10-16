package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.service.AlumnoService;
import com.stacktrace.exam.educationserver.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping(value = "/cursos")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> getAll(){
        return cursoService.getAll();
    }

    @PostMapping(value = "/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody Curso curso) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", cursoService.saveCurso(curso).getId());
        return  mapResponse;

    }
}