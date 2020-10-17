package com.stacktrace.exam.educationserver.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stacktrace.exam.educationserver.entities.Alumno;
import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Persona;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AlumnoDTO {

    private long id;
    private String dni;
    @Enumerated(EnumType.STRING)
    private Persona.DNI_TYPE dni_tipo;
    private String nombre;
    private String apellido;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fecha_de_nacimiento;
    private String domicilio;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Persona.SEX sexo;
    private Set<Integer> cursos_tomados = new HashSet<>();

    public AlumnoDTO(){}
    public AlumnoDTO(Alumno alumno){
        this.id = alumno.getId();
        this.dni = alumno.getDni();
        this.dni_tipo = alumno.getDni_tipo();
        this.nombre = alumno.getNombre();
        this.apellido = alumno.getApellido();
        this.fecha_de_nacimiento = alumno.getFecha_de_nacimiento();
        this.domicilio = alumno.getDomicilio();
        this.telefono = alumno.getTelefono();
        this.sexo = alumno.getSexo();
        this.cursos_tomados.addAll(alumno.getCursos_tomados().stream().map(Curso::getId).collect(Collectors.toSet()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Persona.DNI_TYPE getDni_tipo() {
        return dni_tipo;
    }

    public void setDni_tipo(Persona.DNI_TYPE dni_tipo) {
        this.dni_tipo = dni_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
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

    public Persona.SEX getSexo() {
        return sexo;
    }

    public void setSexo(Persona.SEX sexo) {
        this.sexo = sexo;
    }

    public Set<Integer> getCursos_tomados() {
        return cursos_tomados;
    }

    public void setCursos_tomados(Set<Integer> cursos_tomados) {
        this.cursos_tomados = cursos_tomados;
    }
}
