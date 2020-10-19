package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Capacitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacitacionRepository extends JpaRepository<Capacitacion, Long> {
    void deleteAllByProfesor_Dni(String profesor_dni);
}
