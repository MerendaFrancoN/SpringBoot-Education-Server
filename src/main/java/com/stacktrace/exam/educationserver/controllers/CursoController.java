package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
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
    public ResponseEntity<List<CursoDTO>> getAll(){
        return new ResponseEntity<>(cursoService.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createCurso(@RequestBody CursoDTO curso) {
        Map<String, Object> bodyReturn = new HashMap<>();
        bodyReturn.put("id", cursoService.saveUpdateCurso(curso).getId());
        return new ResponseEntity<>(bodyReturn, HttpStatus.CREATED);
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
            if(cursoReqBody.getAlumnos_dni() != null){
                //Remove alumnos that are already in the course to prevent errors
                cursoReqBody.getAlumnos_dni().removeAll(cursoInDatabase.get().getAlumnos_dni());
                //Clean alumnos
                cursoInDatabase.get().getAlumnos_dni().clear();
                //Add updated ones
                cursoInDatabase.get().getAlumnos_dni().addAll(cursoInDatabase.get().getAlumnos_dni());
            }

            if(cursoReqBody.getProfesor_dni() != null)
                cursoInDatabase.get().setProfesor_dni(cursoReqBody.getProfesor_dni());

            CursoDTO updatedCurso = cursoService.saveUpdateCurso(cursoInDatabase.get());

            if ( updatedCurso == null) {
                return new ResponseEntity<>(
                        new ResponseError(404, String.format("Curso con ID %d No encontrado",
                        cursoReqBody.getId())), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("", HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseError(404, String.format("Curso con ID %d No encontrado",
                        cursoReqBody.getId())), HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/cursos/get_alumnos")
    @ResponseBody
    public Object searchAlumnos(@RequestParam("curso_id") Integer id) {
        Optional<List<AlumnoDTO>> alumnosOfCurso = cursoService.getAlumnosOfCurso(id);
        if(alumnosOfCurso.isPresent()){
            return new ResponseEntity<>(alumnosOfCurso.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Curso con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value="/cursos/{id}")
    @ResponseBody
    public Object removeCurso(@PathVariable Integer id) {
        if(cursoService.exists(id)){
            cursoService.removeCurso(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Curso con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }

}