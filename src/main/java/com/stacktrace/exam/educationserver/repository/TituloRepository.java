package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    public void deleteAllByProfesor_Dni(String profesor_dni);
}
