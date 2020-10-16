package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    Alumno findByDni(@Param("dni") String dni);
}