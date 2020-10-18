package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.AlumnoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stacktrace.exam.educationserver.repository.AlumnoRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    CursoRepository cursoRepository;

    public AlumnoDTO getAlumnoByDNI(String dni){
        return new AlumnoDTO(alumnoRepository.findByDni(dni));
    }

    public List<AlumnoDTO> getAll(){
        return alumnoRepository.findAll().stream().map(AlumnoDTO::new)
                .collect(Collectors.toList());
    }

    public AlumnoDTO saveAlumno(AlumnoDTO alumnoDTO) {

        Alumno alumno = mapAlumnoDTOtoAlumnoEntity(alumnoDTO);
        return new AlumnoDTO(alumnoRepository.save(alumno));
    }

    public void removeAlumno(AlumnoDTO alumnoDTO){
        alumnoRepository.delete(mapAlumnoDTOtoAlumnoEntity(alumnoDTO));
    }

    public List<AlumnoDTO> getAlumnosAprobadosFromCurso(Integer curso_id){
        Optional<Curso> optionalCurso = cursoRepository.findById(curso_id);

        return optionalCurso.map(curso -> curso.getLista_notas().stream()
                .filter(nota -> nota.getNota() >= curso.getNota_aprobacion())
                .map(nota -> new AlumnoDTO(nota.getAlumno()))
                .collect(Collectors.toList()))
            .orElse(null);
    }

    public List<CursoDTO> getCursosAprobadosByAlumno(String alumno_dni){
        Alumno optionalAlumno = alumnoRepository.findByDni(alumno_dni);

        return optionalAlumno.getLista_notas().stream()
                .filter(nota -> nota.getNota() >= nota.getCurso().getNota_aprobacion())
                .map(nota -> new CursoDTO(nota.getCurso()))
                .collect(Collectors.toList());
    }

    public List<CursoDTO> getCursosOfAlumno(String alumno_dni){
        Alumno optionalAlumno = alumnoRepository.findByDni(alumno_dni);
        return optionalAlumno.getCursos_tomados().stream().map(CursoDTO::new).collect(Collectors.toList());
    }

    private Alumno mapAlumnoDTOtoAlumnoEntity(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setId(alumnoDTO.getId());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellido(alumnoDTO.getApellido());
        alumno.setDni(alumnoDTO.getDni());
        alumno.setDni_tipo(alumnoDTO.getDni_tipo());
        alumno.setDomicilio(alumnoDTO.getDomicilio());
        alumno.setTelefono(alumnoDTO.getTelefono());
        alumno.setFecha_de_nacimiento(alumnoDTO.getFecha_de_nacimiento());
        alumno.setSexo(alumnoDTO.getSexo());


        if (null == alumno.getCursos_tomados()) {
            alumno.setCursos_tomados(new HashSet<>());
        }

        alumnoDTO.getCursos_id().forEach(cursoID -> {
            cursoRepository.findById(cursoID).ifPresent(
                    curso -> alumno.getCursos_tomados().add(curso)
            );

        });
        return alumno;
    }

}
