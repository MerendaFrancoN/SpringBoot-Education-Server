package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "profesores", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Profesor extends Persona{

    @OneToMany(mappedBy = "dictadoPor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Curso> cursos_dictados;

    public List<Curso> getCursos_dictados() {
        return cursos_dictados;
    }

    public void setCursos_dictados(List<Curso> cursos_dictados) {
        this.cursos_dictados = cursos_dictados;
    }

}
