package com.stacktrace.exam.educationserver.controllers;

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
    public Object createCurso(@RequestBody CursoDTO curso) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", cursoService.saveUpdateCurso(curso).getId());
        return  mapResponse;
    }

    @PutMapping("/cursos/")
    @ResponseBody
    public Object updateCurso(@RequestBody CursoDTO cursoReqBody) {

        Optional<CursoDTO> cursoInDatabase = cursoService.getCurso(cursoReqBody.getId());
        if(cursoInDatabase.isPresent()){

            if(cursoReqBody.getNombre() != null)
                cursoInDatabase.get().setNombre(cursoReqBody.getNombre());
            if(cursoReqBody.getCant_horas() > 0)
                cursoInDatabase.get().setCant_horas(cursoReqBody.getCant_horas());
            if(cursoReqBody.getDescripcion() != null)
                cursoInDatabase.get().setDescripcion(cursoReqBody.getDescripcion());
            if(cursoReqBody.getNota_aprobacion() > 0)
                cursoInDatabase.get().setNota_aprobacion(cursoReqBody.getNota_aprobacion());
            if(cursoReqBody.getAlumnos() != null){
                //Remove alumnos that are already in the course to prevent errors
                cursoReqBody.getAlumnos().removeAll(cursoInDatabase.get().getAlumnos());
                //Clean alumnos
                cursoInDatabase.get().getAlumnos().clear();
                //Add updated ones
                cursoInDatabase.get().getAlumnos().addAll(cursoInDatabase.get().getAlumnos());
            }

            if(cursoReqBody.getProfesor_dni() != null)
                cursoInDatabase.get().setProfesor_dni(cursoReqBody.getProfesor_dni());

            CursoDTO updatedCurso = cursoService.saveUpdateCurso(cursoReqBody);

            if ( updatedCurso == null) {
                return new ResponseEntity<>(new ResponseError(404, String.format("Curso con ID %d No encontrado", cursoReqBody.getId())), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cursos/get_alumnos")
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

    @DeleteMapping(value="/cursos/{id}")
    @ResponseBody
    public Object removeCurso(@PathVariable Integer id) {
        Optional<CursoDTO> cursoDTO = cursoService.getCurso(id);
        if(cursoDTO.isPresent()){
            cursoService.removeCurso(cursoDTO.get());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Curso con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }

}