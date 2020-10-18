package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Nota;

public class NotaDTO {

    private long id;
    private double nota;
    private Integer curso_id;
    private String alumno_dni;

    public NotaDTO(){}
    public NotaDTO(Nota nota){
        this.setId(nota.getId());
        this.setNota(nota.getNota());
        this.setAlumno_dni(nota.getAlumno().getDni());
        this.setCurso_id(nota.getCurso().getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }

    public String getAlumno_dni() {
        return alumno_dni;
    }

    public void setAlumno_dni(String alumno_dni) {
        this.alumno_dni = alumno_dni;
    }
}
