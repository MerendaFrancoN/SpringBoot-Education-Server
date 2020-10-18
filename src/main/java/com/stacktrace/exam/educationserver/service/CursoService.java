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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

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

    public void removeCurso(CursoDTO cursoDTO){
        cursoRepository.delete(mapCursoDTOtoCursoEntity(cursoDTO));
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