package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.repository.AlumnoRepository;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import com.stacktrace.exam.educationserver.repository.NotaRepository;
import com.stacktrace.exam.educationserver.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private NotaRepository notaRepository;

    public List<CursoDTO> getAll(){
       return cursoRepository.findAll().stream().map(CursoDTO::new).collect(Collectors.toList());
    }

    public Optional<CursoDTO> getCurso(Integer curso_id){
        Optional<Curso> optionalCurso = cursoRepository.findById(curso_id);
        return optionalCurso.map(CursoDTO::new);
    }

    public CursoDTO saveUpdateCurso(CursoDTO cursoDTO) {
        Curso curso = cursoRepository.save(mapCursoDTOtoCursoEntity(cursoDTO));
        return new CursoDTO(curso);
    }

    @Transactional
    public void removeCurso(Integer cursoId){

        //Remove Notas
        notaRepository.deleteAllByCurso_Id(cursoId);
        //Remove Alumnos
        Set<Alumno> alumnosEnlistados = new HashSet<>();
        cursoRepository.findById(cursoId).ifPresent(curso -> alumnosEnlistados.addAll(curso.getAlumnosEnlistados()));
        alumnosEnlistados.forEach(alumno -> {
            alumno.getCursos_tomados().removeIf(curso -> curso.getId() == cursoId);
            alumnoRepository.save(alumno);
        });
        //Remove Curso
        cursoRepository.deleteById(cursoId);
    }

    public boolean exists(Integer curso_id){
        return cursoRepository.existsById(curso_id);
    }

    public Optional<List<AlumnoDTO>> getAlumnosOfCurso(int curso_id){
        Optional<Curso> cursoOptional = cursoRepository.findById(curso_id);

        return cursoOptional.map(curso -> curso.getAlumnosEnlistados().stream()
                .map(AlumnoDTO::new).collect(Collectors.toList()));

    }

    private Curso mapCursoDTOtoCursoEntity(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNombre(cursoDTO.getNombre());
        curso.setDescripcion(cursoDTO.getDescripcion());
        curso.setNota_aprobacion(cursoDTO.getNota_aprobacion());
        curso.setCant_horas(cursoDTO.getCant_horas());

        if(cursoDTO.getProfesor_dni() != null){
            if(cursoDTO.getProfesor_dni().equals(""))
                curso.setDictadoPor(null);
            else
                curso.setDictadoPor(profesorRepository.findByDni(cursoDTO.getProfesor_dni()));
        }

        if (curso.getAlumnosEnlistados() == null) {
            curso.setAlumnosEnlistados(new HashSet<>());
        }
        cursoDTO.getAlumnos_dni().forEach(alumnoDNI -> {
            Alumno alumno = alumnoRepository.findByDni(alumnoDNI);
            alumno.getCursos_tomados().add(curso);
        });
        return curso;
    }

}