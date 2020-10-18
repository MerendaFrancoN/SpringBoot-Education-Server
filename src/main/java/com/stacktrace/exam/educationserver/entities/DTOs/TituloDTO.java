package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.entities.Titulo;

public class TituloDTO {
    private long id;
    private int year;
    private String institucion;
    private String title;
    private String profesor_dni;

    public TituloDTO(){}

    public TituloDTO(Titulo titulo){
        this.id = titulo.getId();
        this.year = titulo.getYear();
        this.institucion = titulo.getInstitucion();
        this.title = titulo.getTitle();
        this.setProfesor_dni(titulo.getProfesor().getDni());
    }

    public Titulo toTitulo(Profesor profesor){
        Titulo titulo = new Titulo();
        titulo.setId(this.getId());
        titulo.setProfesor(profesor);
        titulo.setInstitucion(this.getInstitucion());
        titulo.setTitle(this.getTitle());
        return titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfesor_dni() {
        return profesor_dni;
    }

    public void setProfesor_dni(String profesor_dni) {
        this.profesor_dni = profesor_dni;
    }
}
