package com.stacktrace.exam.educationserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String descripcion;
    private long cant_horas;
    private double nota_final;

    @ManyToMany(mappedBy = "cursos_tomados", targetEntity = Alumno.class)
    @JsonIgnore
    private Set<Alumno> alumnosEnlistados;

    //Asumo que uno o varios cursos pueden ser dictado por un solo profesor.
    @ManyToOne
    @JsonIgnore
    private Profesor dictadoPor;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getNota_final() {
        return nota_final;
    }

    public void setNota_final(double nota_final) {
        this.nota_final = nota_final;
    }
}
