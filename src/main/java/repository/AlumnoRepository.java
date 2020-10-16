package repository;

import entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {
    Alumno findByDni(@Param("dni") String dni);
}