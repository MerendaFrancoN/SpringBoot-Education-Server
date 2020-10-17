package com.stacktrace.exam.educationserver.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Persona;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlumnoDTO extends PersonaDTO{

    private Set<Integer> cursos_tomados = new HashSet<>();

    public AlumnoDTO(){}
    public AlumnoDTO(Alumno alumno){
        super();
        super.setId(alumno.getId());
        super.setDni(alumno.getDni());
        super.setDni_tipo(alumno.getDni_tipo());
        super.setNombre(alumno.getNombre());
        super.setApellido(alumno.getApellido());
        super.setFecha_de_nacimiento(alumno.getFecha_de_nacimiento());
        super.setDomicilio(alumno.getDomicilio());
        super.setTelefono(alumno.getTelefono());
        super.setSexo(alumno.getSexo());
        this.cursos_tomados.addAll(alumno.getCursos_tomados().stream().map(Curso::getId).collect(Collectors.toSet()));
    }

    public Set<Integer> getCursos_tomados() {
        return cursos_tomados;
    }

    public void setCursos_tomados(Set<Integer> cursos_tomados) {
        this.cursos_tomados = cursos_tomados;
    }
}
