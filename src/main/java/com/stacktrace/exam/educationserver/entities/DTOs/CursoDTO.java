package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Nota;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CursoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private long cant_horas;
    private double nota_aprobacion;
    private Set<String> alumnos_dni = new HashSet<>();
    private String profesor_dni;
    private List<Long> lista_notas_id = new LinkedList<>();

    public CursoDTO(){}
    public CursoDTO(Curso curso){
        this.setId(curso.getId());
        this.setNombre(curso.getNombre());
        this.setDescripcion(curso.getDescripcion());
        this.setCant_horas(curso.getCant_horas());
        this.setNota_aprobacion(curso.getNota_aprobacion());
        this.setAlumnos_dni(curso.getAlumnosEnlistados().stream()
                .map(Alumno::getDni).collect(Collectors.toSet()));

        if(curso.getLista_notas() != null)
            this.setLista_notas_id(curso.getLista_notas().stream()
                    .map(Nota::getId).collect(Collectors.toList()));

        try{
            if(curso.getDictadoPor().getDni().equals(""))
                this.setProfesor_dni("");
            else
                this.setProfesor_dni(curso.getDictadoPor().getDni());
        }catch (NullPointerException e){
            this.setProfesor_dni(null);
        }

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

    public Set<String> getAlumnos_dni() {
        return alumnos_dni;
    }

    public void setAlumnos_dni(Set<String> alumnos_dni) {
        this.alumnos_dni = alumnos_dni;
    }

    public String getProfesor_dni() {
        return profesor_dni;
    }

    public void setProfesor_dni(String profesor_dni) {
        this.profesor_dni = profesor_dni;
    }

    public List<Long> getLista_notas_id() {
        return lista_notas_id;
    }

    public void setLista_notas_id(List<Long> lista_notas_id) {
        this.lista_notas_id = lista_notas_id;
    }
}
