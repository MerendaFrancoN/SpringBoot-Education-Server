package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> getAll(){
        return cursoRepository.findAll();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

}