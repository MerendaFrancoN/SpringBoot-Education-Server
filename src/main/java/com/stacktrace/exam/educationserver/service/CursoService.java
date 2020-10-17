package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.repository.AlumnoRepository;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<CursoDTO> getAll(){
       return cursoRepository.findAll().stream().map(CursoDTO::new).collect(Collectors.toList());
    }

    public CursoDTO saveCurso(CursoDTO cursoDTO) {
        Curso curso = cursoRepository.save(mapCursoDTOtoCursoEntity(cursoDTO));
        return new CursoDTO(curso);
    }

    public Optional<CursoDTO> getCurso(Integer curso_id){
        Optional<Curso> optionalCurso = cursoRepository.findById(curso_id);
        return optionalCurso.map(CursoDTO::new);
    }

    public CursoDTO updateCurso(CursoDTO updatedCurso){
        Optional<CursoDTO> cursoToUpdateDTO = getCurso(updatedCurso.getId());

        //CONTROLS
        if (cursoToUpdateDTO.isPresent()){

            if(updatedCurso.getNombre() != null)
                cursoToUpdateDTO.get().setNombre(updatedCurso.getNombre());
            if(updatedCurso.getDescripcion() != null)
                cursoToUpdateDTO.get().setDescripcion(updatedCurso.getDescripcion());
            if(updatedCurso.getAlumnos() != null)
                cursoToUpdateDTO.get().getAlumnos().addAll(updatedCurso.getAlumnos());
            //Update Values
            cursoToUpdateDTO.get().setId(updatedCurso.getId());

            return new CursoDTO(cursoRepository.save(mapCursoDTOtoCursoEntity(cursoToUpdateDTO.get())));
        }
        return null;

    }

    private Curso mapCursoDTOtoCursoEntity(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNombre(cursoDTO.getNombre());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setNota_aprobacion(cursoDTO.getNota_aprobacion());
        curso.setCant_horas(cursoDTO.getCant_horas());

        if (null == curso.getAlumnosEnlistados()) {
            curso.setAlumnosEnlistados(new HashSet<>());
        }
        cursoDTO.getAlumnos().forEach(alumnoDNI -> {
            Alumno alumno = alumnoRepository.findByDni(alumnoDNI);
            alumno.getCursos_tomados().add(curso);
        });
        return curso;
    }

}