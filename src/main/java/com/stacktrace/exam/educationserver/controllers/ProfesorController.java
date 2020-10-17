package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.ProfesorDTO;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping(value = "/profesores/get_cursos")
    @ResponseBody
    public Object searchCursos(@RequestParam("dni") String dni) {
        ProfesorDTO profesorDTO = profesorService.getProfesor(dni);
        if(profesorDTO != null){
            return new ResponseEntity<>(profesorDTO.getCursos_dictados_id(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Profesor con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }

    @PutMapping("/profesores/")
    @ResponseBody
    public Object updateProfesor(@RequestBody ProfesorDTO profesorDTO) {

        Map<String, Object> mapResponse = new HashMap<>();
        ProfesorDTO profesorToBeUpdated = profesorService.getProfesor(profesorDTO.getDni());

        if(profesorDTO.getNombre() != null)
            profesorToBeUpdated.setNombre(profesorDTO.getNombre());
        if(profesorDTO.getApellido() != null)
            profesorToBeUpdated.setApellido(profesorDTO.getApellido());
        if(profesorDTO.getDomicilio() != null)
            profesorToBeUpdated.setDomicilio(profesorDTO.getDomicilio());
        if(profesorDTO.getSexo() != null)
            profesorToBeUpdated.setSexo(profesorDTO.getSexo());
        if(profesorDTO.getFecha_de_nacimiento() != null)
            profesorToBeUpdated.setFecha_de_nacimiento(profesorDTO.getFecha_de_nacimiento());
        if(profesorDTO.getTelefono() != null)
            profesorToBeUpdated.setTelefono(profesorDTO.getTelefono());
        if(profesorDTO.getCursos_dictados_id() != null){
            profesorToBeUpdated.getCursos_dictados_id().clear();
            profesorToBeUpdated.getCursos_dictados_id().addAll(profesorDTO.getCursos_dictados_id());
        }

        ProfesorDTO updated = profesorService.saveProfesor(profesorToBeUpdated);

        if ( updated == null) {
            return new ResponseEntity<>(new ResponseError(404, String.format("Profesor con dni %s No encontrado", profesorDTO.getDni())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @DeleteMapping(value = "/profesores/{dni}")
    @ResponseBody
    public Object removeProfesor(@PathVariable String dni) {
        ProfesorDTO profesorDTO = profesorService.getProfesor(dni);
        if(profesorDTO != null){
            profesorService.removeProfesor(profesorDTO);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }
}