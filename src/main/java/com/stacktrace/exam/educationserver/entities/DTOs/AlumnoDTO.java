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

    private Set<Integer> cursos_tomados = new HashSet<>();

    private List<Long> lista_notas = new LinkedList<>();

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
        this.getCursos_tomados().addAll(alumno.getCursos_tomados()
                .stream().map(Curso::getId).collect(Collectors.toSet()));
        this.getLista_notas().addAll(alumno.getLista_notas()
                .stream().map(Nota::getId).collect(Collectors.toList()));

    }

    public Set<Integer> getCursos_tomados() {
        return cursos_tomados;
    }

    public void setCursos_tomados(Set<Integer> cursos_tomados) {
        this.cursos_tomados = cursos_tomados;
    }

    public List<Long> getLista_notas() {
        return lista_notas;
    }

    public void setLista_notas(List<Long> lista_notas) {
        this.lista_notas = lista_notas;
    }
}
