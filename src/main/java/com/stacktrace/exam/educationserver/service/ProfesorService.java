package com.stacktrace.exam.educationserver.service;


import com.stacktrace.exam.educationserver.entities.DTOs.ProfesorDTO;
import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import com.stacktrace.exam.educationserver.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    CursoRepository cursoRepository;

    public ProfesorDTO getProfesor(String dni){
        return new ProfesorDTO(profesorRepository.findByDni(dni));
    }

    public Set<ProfesorDTO> getAll(){
        return profesorRepository.findAll().stream().map(ProfesorDTO::new).collect(Collectors.toSet());
    }

    public ProfesorDTO saveProfesor(ProfesorDTO profesor) {
        return new ProfesorDTO(profesorRepository.save(mapProfesorDTOtoProfesorEntity(profesor)));
    }

    public void removeProfesor(ProfesorDTO profesorDTO){
        Profesor profesor = mapProfesorDTOtoProfesorEntity(profesorDTO);
        profesorRepository.delete(profesor);
    }

    private Profesor mapProfesorDTOtoProfesorEntity(ProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();
        profesor.setId(profesorDTO.getId());
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellido(profesorDTO.getApellido());
        profesor.setDni(profesorDTO.getDni());
        profesor.setDni_tipo(profesorDTO.getDni_tipo());
        profesor.setDomicilio(profesorDTO.getDomicilio());
        profesor.setTelefono(profesorDTO.getTelefono());
        profesor.setFecha_de_nacimiento(profesorDTO.getFecha_de_nacimiento());
        profesor.setSexo(profesorDTO.getSexo());


        if (null == profesor.getCursos_dictados()) {
            profesor.setCursos_dictados(new HashSet<>());
        }

        profesorDTO.getCursos_dictados().forEach(cursoID -> {
            cursoRepository.findById(cursoID).ifPresent(
                    curso -> profesor.getCursos_dictados().add(curso)
            );

        });
        return profesor;
    }

}