package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    public List<Profesor> getAll(){
        return profesorRepository.findAll();
    }

    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

}