package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    void deleteAllByAlumno_Dni(String alumno_dni);
    void deleteAllByCurso_Id(Integer id);
}
