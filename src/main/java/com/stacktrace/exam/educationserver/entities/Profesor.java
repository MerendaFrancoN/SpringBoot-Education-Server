package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "profesores", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
public class Profesor extends Persona{

    @OneToMany(mappedBy = "dictadoPor", cascade = CascadeType.REFRESH)
    private Set<Curso> cursos_dictados;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.REFRESH)
    private List<Capacitacion> capacitaciones = new LinkedList<>();

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.REFRESH)
    private List<Titulo> titulos = new LinkedList<>();


    public Set<Curso> getCursos_dictados() {
        return cursos_dictados;
    }

    public void setCursos_dictados(Set<Curso> cursos_dictados) {
        this.cursos_dictados = cursos_dictados;
    }

    public List<Capacitacion> getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(List<Capacitacion> capacitaciones) {
        this.capacitaciones = capacitaciones;
    }

    public List<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulo> titulos) {
        this.titulos = titulos;
    }


}
