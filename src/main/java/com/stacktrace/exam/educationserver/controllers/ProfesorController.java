package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.ProfesorDTO;
import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @GetMapping(value = "/profesores")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Set<ProfesorDTO> getAll(){
        return profesorService.getAll();
    }

    @PostMapping(value = "/profesores")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createProfesor(@RequestBody ProfesorDTO profesor) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", profesorService.saveProfesor(profesor).getId());
        return  mapResponse;
    }

    @GetMapping(value = "/profesores/busqueda")
    @ResponseBody
    public Object searchCursos(@RequestParam("dni") String dni) {
        ProfesorDTO profesorDTO = profesorService.getProfesor(dni);
        if(profesorDTO != null){
            return new ResponseEntity<>(profesorDTO.getCursos_dictados(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Profesor con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }
}