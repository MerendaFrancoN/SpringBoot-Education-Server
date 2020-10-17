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

    private Set<Integer> cursos_tomados_id = new HashSet<>();

    private List<Long> lista_notas_id = new LinkedList<>();

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
        this.getCursos_tomados_id().addAll(alumno.getCursos_tomados()
                .stream().map(Curso::getId).collect(Collectors.toSet()));

        if(alumno.getLista_notas() != null) {
            this.getLista_notas_id().addAll(alumno.getLista_notas()
                .stream().map(Nota::getId).collect(Collectors.toList()));
        }
    }

    public Set<Integer> getCursos_tomados_id() {
        return cursos_tomados_id;
    }

    public void setCursos_tomados_id(Set<Integer> cursos_tomados_id) {
        this.cursos_tomados_id = cursos_tomados_id;
    }

    public List<Long> getLista_notas_id() {
        return lista_notas_id;
    }

    public void setLista_notas_id(List<Long> lista_notas_id) {
        this.lista_notas_id = lista_notas_id;
    }
}
