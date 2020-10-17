package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Nota;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlumnoDTO extends PersonaDTO{

    private Set<Integer> cursos_id = new HashSet<>();

    private List<Long> notas_id = new LinkedList<>();

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
        this.getCursos_id().addAll(alumno.getCursos_tomados()
                .stream().map(Curso::getId).collect(Collectors.toSet()));

        if(alumno.getLista_notas() != null) {
            this.getNotas_id().addAll(alumno.getLista_notas()
                .stream().map(Nota::getId).collect(Collectors.toList()));
        }
    }

    public Set<Integer> getCursos_id() {
        return cursos_id;
    }

    public void setCursos_id(Set<Integer> cursos_id) {
        this.cursos_id = cursos_id;
    }

    public List<Long> getNotas_id() {
        return notas_id;
    }

    public void setNotas_id(List<Long> notas_id) {
        this.notas_id = notas_id;
    }
}
