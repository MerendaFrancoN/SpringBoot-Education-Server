package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping(value = "/cursos")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CursoDTO> getAll(){
        return cursoService.getAll();
    }

    @PostMapping(value = "/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createClient(@RequestBody CursoDTO curso) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", cursoService.saveCurso(curso).getId());
        return  mapResponse;
    }


    @PutMapping("/cursos/{id}")
    @ResponseBody
    public Object updateCurso(@RequestBody CursoDTO curso, @PathVariable Integer id) {

        Map<String, Object> mapResponse = new HashMap<>();

        curso.setId(id);
        CursoDTO updatedCurso = cursoService.updateCurso(curso);

        if ( updatedCurso == null) {
            return new ResponseEntity<>(new ResponseError(404, String.format("Curso con ID %d No encontrado", curso.getId())), HttpStatus.NOT_FOUND);
        }
        mapResponse.put("nombre",updatedCurso.getNombre());
        mapResponse.put("alumnosEnlistados", updatedCurso.getAlumnos());

        return mapResponse;
    }

    @GetMapping(value = "/cursos/busqueda")
    @ResponseBody
    public Object searchAlumnos(@RequestParam("curso_id") Integer id) {
        Optional<CursoDTO> cursoOptional = cursoService.getCurso(id);
        if(cursoOptional.isPresent()){
            return new ResponseEntity<>(cursoOptional.get().getAlumnos(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Curso con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }

}