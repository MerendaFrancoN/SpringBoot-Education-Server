package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alumnos", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Alumno {

    public enum SEX {
        MASCULINO,
        FEMENINO,
        OTRO
    }

    public enum DNI_TYPE{
        DNI,
        LE,
        LC,
        CI
    }

    public Alumno(){}

    @Id
    private String dni;

    @Enumerated(EnumType.STRING)
    private DNI_TYPE dni_tipo;

    private LocalDate dateTime;
    private String domicilio;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private SEX estado;

    /*Getters and setters*/
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public DNI_TYPE getDni_tipo() {
        return dni_tipo;
    }

    public void setDni_tipo(DNI_TYPE dni_tipo) {
        this.dni_tipo = dni_tipo;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public SEX getEstado() {
        return estado;
    }

    public void setEstado(SEX estado) {
        this.estado = estado;
    }

}
