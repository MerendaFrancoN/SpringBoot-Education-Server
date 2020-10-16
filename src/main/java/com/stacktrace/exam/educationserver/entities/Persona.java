package com.stacktrace.exam.educationserver.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni"})})
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
public class Persona {

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
    public Persona(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dni;
    @Enumerated(EnumType.STRING)
    private DNI_TYPE dni_tipo;
    private String nombre;
    private String apellido;
    private LocalDate fecha_de_nacimiento;
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

    public LocalDate getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(LocalDate dateTime) {
        this.fecha_de_nacimiento = dateTime;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
