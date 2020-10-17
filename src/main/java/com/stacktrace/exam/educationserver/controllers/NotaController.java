package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.NotaDTO;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class NotaController {

    @Autowired
    NotaService notaService;

    @GetMapping(value = "/notas")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<NotaDTO> getAll(){
        return notaService.getAll();
    }

    @PostMapping(value = "/notas")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createNota(@RequestBody NotaDTO notaDTO) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("id", notaService.saveUpdateNota(notaDTO).getId());
        return  mapResponse;

    }

    @PutMapping("/notas")
    @ResponseBody
    public Object updateNota(@RequestBody NotaDTO notaDTO) {

        Optional<NotaDTO> notaToBeUpdated = notaService.getNotaById(notaDTO.getId());

        if ( notaToBeUpdated.isPresent()) {
            if(notaDTO.getNota() > 0) notaToBeUpdated.get().setNota(notaDTO.getNota());
            if(notaDTO.getAlumno_dni()!= null) notaToBeUpdated.get().setAlumno_dni(notaDTO.getAlumno_dni());
            if(notaDTO.getCurso_id()!= null) notaToBeUpdated.get().setCurso_id(notaDTO.getCurso_id());

            return new ResponseEntity<>(notaToBeUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseError(404, String.format("Nota con id %s no encontrada", notaDTO.getId())), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/notas/{id}")
    @ResponseBody
    public Object removeNota(@PathVariable Long id) {
        Optional<NotaDTO> notaDTO = notaService.getNotaById(id);
        if(notaDTO.isPresent())
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Nota con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }
}
