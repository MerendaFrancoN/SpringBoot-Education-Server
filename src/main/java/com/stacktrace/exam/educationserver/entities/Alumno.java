package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "alumnos", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Alumno extends Persona{


    //Table cursos_alumnos will hold data from join between cursos and alumnos
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "cursos_alumnos",
            joinColumns = {
                    @JoinColumn(name = "alumno_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "curso_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Curso> cursos_tomados;

    @OneToMany(mappedBy = "alumno",cascade = CascadeType.REFRESH)
    private List<Nota> lista_notas;

    public Set<Curso> getCursos_tomados() {
        return cursos_tomados;
    }

    public void setCursos_tomados(Set<Curso> cursos_tomados) {
        this.cursos_tomados = cursos_tomados;
    }

    public List<Nota> getLista_notas() {
        return lista_notas;
    }

    public void setLista_notas(List<Nota> lista_notas) {
        this.lista_notas = lista_notas;
    }
}
