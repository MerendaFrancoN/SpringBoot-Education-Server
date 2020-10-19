package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.ProfesorDTO;
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

    @GetMapping(value = "/profesores/get_cursos")
    @ResponseBody
    public Object searchCursos(@RequestParam("dni") String dni) {
        List<CursoDTO> cursoDTOS = profesorService.getCursosOfProfesor(dni);
        if(cursoDTOS != null){
            return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Profesor con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }

    @PutMapping("/profesores/")
    @ResponseBody
    public Object updateProfesor(@RequestBody ProfesorDTO profesorDTO) {

        ProfesorDTO profesorToBeUpdated = profesorService.getProfesor(profesorDTO.getDni());
        validateInput(profesorDTO, profesorToBeUpdated);
        ProfesorDTO updated = profesorService.updateProfesor(profesorToBeUpdated);

        if ( updated == null) {
            return new ResponseEntity<>(new ResponseError(404, String.format("Profesor con dni %s No encontrado", profesorDTO.getDni())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping(value = "/profesores/{dni}")
    @ResponseBody
    public Object removeProfesor(@PathVariable String dni) {
        if(profesorService.exists(dni)){
            profesorService.removeProfesor(dni);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }

    private void validateInput(ProfesorDTO profesorDTO_RequestBody, ProfesorDTO profesorToBeUpdated) {
        if(profesorDTO_RequestBody.getNombre() != null)
            profesorToBeUpdated.setNombre(profesorDTO_RequestBody.getNombre());
        if(profesorDTO_RequestBody.getApellido() != null)
            profesorToBeUpdated.setApellido(profesorDTO_RequestBody.getApellido());
        if(profesorDTO_RequestBody.getDomicilio() != null)
            profesorToBeUpdated.setDomicilio(profesorDTO_RequestBody.getDomicilio());
        if(profesorDTO_RequestBody.getSexo() != null)
            profesorToBeUpdated.setSexo(profesorDTO_RequestBody.getSexo());
        if(profesorDTO_RequestBody.getFecha_de_nacimiento() != null)
            profesorToBeUpdated.setFecha_de_nacimiento(profesorDTO_RequestBody.getFecha_de_nacimiento());
        if(profesorDTO_RequestBody.getTelefono() != null)
            profesorToBeUpdated.setTelefono(profesorDTO_RequestBody.getTelefono());
        if(profesorDTO_RequestBody.getCursos_id() != null){
            profesorToBeUpdated.getCursos_id().clear();
            profesorToBeUpdated.getCursos_id().addAll(profesorDTO_RequestBody.getCursos_id());
        }
        if(profesorDTO_RequestBody.getTitulos() != null){
            profesorToBeUpdated.getTitulos().clear();
            profesorToBeUpdated.getTitulos().addAll(profesorDTO_RequestBody.getTitulos());
        }
        if(profesorDTO_RequestBody.getCapacitaciones() != null){
            profesorToBeUpdated.getCapacitaciones().clear();
            profesorToBeUpdated.getCapacitaciones().addAll(profesorDTO_RequestBody.getCapacitaciones());
        }
    }
}