package com.stacktrace.exam.educationserver.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private long cant_horas;
    private double nota_aprobacion;

    @ManyToMany(mappedBy = "cursos_tomados")
    private Set<Alumno> alumnosEnlistados = new HashSet<>();

    //Asumo que uno o varios cursos pueden ser dictado por un solo profesor.
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profesor dictadoPor;


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

    public void setNota_aprobacion(double nota_final) {
        this.nota_aprobacion = nota_final;
    }

    public Set<Alumno> getAlumnosEnlistados() {
        return alumnosEnlistados;
    }

    public void setAlumnosEnlistados(Set<Alumno> alumnosEnlistados) {
        this.alumnosEnlistados = alumnosEnlistados;
    }

    public Profesor getDictadoPor() {
        return dictadoPor;
    }

    public void setDictadoPor(Profesor dictadoPor) {
        this.dictadoPor = dictadoPor;
    }

}
