package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "profesores", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Profesor extends Persona{

    @OneToMany(mappedBy = "dictadoPor", cascade = CascadeType.ALL)
    private Set<Curso> cursos_dictados;

    public Set<Curso> getCursos_dictados() {
        return cursos_dictados;
    }

    public void setCursos_dictados(Set<Curso> cursos_dictados) {
        this.cursos_dictados = cursos_dictados;
    }

}
