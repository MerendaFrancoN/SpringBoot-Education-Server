package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CursoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private long cant_horas;
    private double nota_aprobacion;
    private Set<String> alumnos = new HashSet<>();
    private Set<String> profesores = new HashSet<>();

    public CursoDTO(){}
    public CursoDTO(Curso curso){
        this.setId(curso.getId());
        this.setNombre(curso.getNombre());
        this.setDescripcion(curso.getDescripcion());
        this.setCant_horas(curso.getCant_horas());
        this.setNota_aprobacion(curso.getNota_aprobacion());
        this.setAlumnos(curso.getAlumnosEnlistados().stream().map(Alumno::getDni).collect(Collectors.toSet()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getCant_horas() {
        return cant_horas;
    }

    public void setCant_horas(long cant_horas) {
        this.cant_horas = cant_horas;
    }

    public double getNota_aprobacion() {
        return nota_aprobacion;
    }

    public void setNota_aprobacion(double nota_aprobacion) {
        this.nota_aprobacion = nota_aprobacion;
    }

    public Set<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<String> alumnos) {
        this.alumnos = alumnos;
    }

    public Set<String> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<String> profesores) {
        this.profesores = profesores;
    }
}
