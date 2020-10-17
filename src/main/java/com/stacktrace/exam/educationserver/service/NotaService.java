package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.NotaDTO;
import com.stacktrace.exam.educationserver.entities.Nota;
import com.stacktrace.exam.educationserver.repository.AlumnoRepository;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import com.stacktrace.exam.educationserver.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {
    @Autowired
    NotaRepository notaRepository;

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    CursoRepository cursoRepository;

    public List<NotaDTO> getAll(){
        return notaRepository.findAll().stream()
                .map(NotaDTO::new).collect(Collectors.toList());
    }

    public NotaDTO saveUpdateNota(NotaDTO notaDTO) {
        Nota nota = notaRepository.save(mapNotaDTOtoNota(notaDTO));
        return new NotaDTO(nota);
    }

    public Optional<NotaDTO> getNotaById(Long nota_id){
        Optional<Nota> optionalNota = notaRepository.findById(nota_id);
        return optionalNota.map(NotaDTO::new);
    }

    public void removeNota(NotaDTO notaDTO){
        notaRepository.delete(mapNotaDTOtoNota(notaDTO));
    }

    private Nota mapNotaDTOtoNota(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setNota(notaDTO.getNota());
        nota.setAlumno(alumnoRepository.findByDni(notaDTO.getAlumno_dni()));
        cursoRepository.findById(notaDTO.getCurso_id()).ifPresent(nota::setCurso);
        return nota;
    }
}
