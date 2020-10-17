package com.stacktrace.exam.educationserver.controllers;

import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.ResponseError;
import com.stacktrace.exam.educationserver.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<AlumnoDTO> getAll(){
        return alumnoService.getAll();
    }

    @PostMapping(value = "/alumnos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createAlumno(@RequestBody AlumnoDTO alumno) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("dni", alumnoService.saveAlumno(alumno).getDni());
        return  mapResponse;

    }

    @GetMapping(value = "/alumnos/get_cursos")
    @ResponseBody
    public Object searchCursos(@RequestParam("dni") String dni) {
        AlumnoDTO alumnoDTO = alumnoService.getAlumnoByDNI(dni);
        if(alumnoDTO != null){
            return new ResponseEntity<>(alumnoDTO.getCursos_id(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }

    @PutMapping("/alumnos/")
    @ResponseBody
    public Object updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {

        Map<String, Object> mapResponse = new HashMap<>();
        AlumnoDTO alumnoToBeUpdated = alumnoService.getAlumnoByDNI(alumnoDTO.getDni());

        if(alumnoDTO.getNombre() != null)
            alumnoToBeUpdated.setNombre(alumnoDTO.getNombre());
        if(alumnoDTO.getApellido() != null)
            alumnoToBeUpdated.setApellido(alumnoDTO.getApellido());
        if(alumnoDTO.getDomicilio() != null)
            alumnoToBeUpdated.setDomicilio(alumnoDTO.getDomicilio());
        if(alumnoDTO.getSexo() != null)
            alumnoToBeUpdated.setSexo(alumnoDTO.getSexo());
        if(alumnoDTO.getFecha_de_nacimiento() != null)
            alumnoToBeUpdated.setFecha_de_nacimiento(alumnoDTO.getFecha_de_nacimiento());
        if(alumnoDTO.getTelefono() != null)
            alumnoToBeUpdated.setTelefono(alumnoDTO.getTelefono());
        if(alumnoDTO.getCursos_id() != null){
            alumnoToBeUpdated.getCursos_id().clear();
            alumnoToBeUpdated.getCursos_id().addAll(alumnoDTO.getCursos_id());
        }

        AlumnoDTO updated = alumnoService.saveAlumno(alumnoToBeUpdated);

        if ( updated == null) {
            return new ResponseEntity<>(new ResponseError(404, String.format("Alumno con dni %s No encontrado", alumnoDTO.getDni())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping(value = "/alumnos/{dni}")
    @ResponseBody
    public Object removeAlumno(@PathVariable String dni) {
        AlumnoDTO alumnoDTO = alumnoService.getAlumnoByDNI(dni);
        if(alumnoDTO != null){
            alumnoService.removeAlumno(alumnoDTO);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/alumnos/get_aprobados")
    @ResponseBody
    public Object getAprobadosFromCurso(@RequestParam("curso_id") Integer id) {
        List<AlumnoDTO> alumnosAprobadosFromCurso = alumnoService.getAlumnosAprobadosFromCurso(id);
        if(alumnosAprobadosFromCurso != null){
            return new ResponseEntity<>(alumnosAprobadosFromCurso, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Curso con id %d no encontrado", id)),
                    HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/alumnos/get_cursos_aprobados")
    @ResponseBody
    public Object getCursosAprobadosFromAlumno(@RequestParam("dni") String dni) {
        List<CursoDTO> cursosAprobadosByAlumno = alumnoService.getCursosAprobadosByAlumno(dni);
        if(cursosAprobadosByAlumno != null){
            return new ResponseEntity<>(cursosAprobadosByAlumno, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ResponseError
                    (404, String.format("Alumno con dni %s no encontrado", dni)),
                    HttpStatus.NOT_FOUND);
    }

}
