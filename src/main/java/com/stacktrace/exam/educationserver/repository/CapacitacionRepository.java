package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Capacitacion;
import com.stacktrace.exam.educationserver.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacitacionRepository extends JpaRepository<Capacitacion, Long> {
    public void deleteAllByProfesor_Dni(String profesor_dni);
}
