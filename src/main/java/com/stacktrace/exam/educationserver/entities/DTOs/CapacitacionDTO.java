package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Capacitacion;
import com.stacktrace.exam.educationserver.entities.Profesor;

public class CapacitacionDTO {
    private long id;
    private int year;
    private String institucion;
    private String title;
    private String descripcion;
    private String profesor_dni;

    public CapacitacionDTO(){}
    public CapacitacionDTO(Capacitacion capacitacion){
        this.id = capacitacion.getId();
        this.year = capacitacion.getYear();
        this.institucion = capacitacion.getInstitucion();
        this.title = capacitacion.getTitle();
        this.descripcion = capacitacion.getDescripcion();
        this.profesor_dni = capacitacion.getProfesor().getDni();
    }

    public Capacitacion toCapacitacion(Profesor profesor){
        Capacitacion capacitacion = new Capacitacion();
        capacitacion.setId(this.getId());
        capacitacion.setProfesor(profesor);
        capacitacion.setDescripcion(this.getDescripcion());
        capacitacion.setInstitucion(this.getInstitucion());
        capacitacion.setTitle(this.getTitle());
        return capacitacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProfesor_dni() {
        return profesor_dni;
    }

    public void setProfesor_dni(String profesor_dni) {
        this.profesor_dni = profesor_dni;
    }
}
