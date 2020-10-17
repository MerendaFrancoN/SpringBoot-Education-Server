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

    public Optional<Curso> getCurso(Integer curso_id){
        return cursoRepository.findById(curso_id);
    }

    public Curso updateCurso(Curso updatedCurso){
        Curso cursoToUpdate = cursoRepository.findById(updatedCurso.getId()).orElse(null);

        //CONTROLS
        if (cursoToUpdate ==  null){
            return null;
        }
        if(updatedCurso.getNombre() != null)
            cursoToUpdate.setNombre(updatedCurso.getNombre());
        if(updatedCurso.getDescripcion() != null)
            cursoToUpdate.setDescripcion(updatedCurso.getDescripcion());
        if(updatedCurso.getAlumnosEnlistados() != null)
            cursoToUpdate.getAlumnosEnlistados().addAll(updatedCurso.getAlumnosEnlistados());

        return cursoRepository.save(cursoToUpdate);
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