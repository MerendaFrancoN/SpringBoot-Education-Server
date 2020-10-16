package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.service.CursoService;
import com.stacktrace.exam.educationserver.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @GetMapping(value = "/profesor")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Profesor> getAll(){
        return profesorService.getAll();
    }

    @PostMapping(value = "/profesor")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody Profesor profesor) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", profesorService.saveProfesor(profesor).getId());
        return  mapResponse;

    }
}