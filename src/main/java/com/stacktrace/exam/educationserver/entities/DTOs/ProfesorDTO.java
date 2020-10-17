package com.stacktrace.exam.educationserver.entities.DTOs;
import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Profesor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfesorDTO extends PersonaDTO{

    private Set<Integer> cursos_dictados = new HashSet<>();

    public ProfesorDTO(){}
    public ProfesorDTO(Profesor profesor){
        super();
        super.setId(profesor.getId());
        super.setDni(profesor.getDni());
        super.setDni_tipo(profesor.getDni_tipo());
        super.setNombre(profesor.getNombre());
        super.setApellido(profesor.getApellido());
        super.setFecha_de_nacimiento(profesor.getFecha_de_nacimiento());
        super.setDomicilio(profesor.getDomicilio());
        super.setTelefono(profesor.getTelefono());
        super.setSexo(profesor.getSexo());

        this.cursos_dictados.addAll(profesor.getCursos_dictados().stream().map(Curso::getId).collect(Collectors.toSet()));
    }

    public Set<Integer> getCursos_dictados() {
        return cursos_dictados;
    }

    public void setCursos_dictados(Set<Integer> cursos_dictados) {
        this.cursos_dictados = cursos_dictados;
    }
}
