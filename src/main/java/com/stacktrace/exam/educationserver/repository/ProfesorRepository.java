package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByDni(@Param("dni") String dni);
    boolean existsByDni(String dni);
    void deleteByDni(String dni);
}
