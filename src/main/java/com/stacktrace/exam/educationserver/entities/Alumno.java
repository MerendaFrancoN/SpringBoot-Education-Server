package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumnos", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Alumno extends Persona{


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "cursos_alumnos",
            joinColumns = {
                    @JoinColumn(name = "alumno_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "curso_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Curso> cursos_tomados;

    public List<Curso> getCursos_tomados() {
        return cursos_tomados;
    }

    public void setCursos_tomados(List<Curso> cursos_tomados) {
        this.cursos_tomados = cursos_tomados;
    }
}
