package com.stacktrace.exam.educationserver.repository;

import com.stacktrace.exam.educationserver.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}