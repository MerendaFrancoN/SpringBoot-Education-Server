package com.stacktrace.exam.educationserver.entities.DTOs;

import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.Profesor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfesorDTO extends PersonaDTO{

    private Set<Integer> cursos_id = new HashSet<>();

    private List<CapacitacionDTO> capacitaciones = new LinkedList<>();
    private List<TituloDTO> titulos = new LinkedList<>();

    public ProfesorDTO(){}
    public ProfesorDTO(Profesor profesor){
        super();
        super.setId(profesor.getId());
        super.setDni(profesor.getDni());
        super.setDni_tipo(profesor.getDni_tipo());
        super.setNombre(profesor.getNombre());
        super.setApellido(profesor.getApellido());
        super.setFecha_de_nacimiento(profesor.getFecha_de_nacimiento());
        super.setDomicilio(profesor.getDomicilio());
        super.setTelefono(profesor.getTelefono());
        super.setSexo(profesor.getSexo());

        if(profesor.getCapacitaciones() != null)
            this.capacitaciones.addAll(profesor.getCapacitaciones().stream()
                    .map(CapacitacionDTO::new).collect(Collectors.toList()));
        if(profesor.getTitulos() != null)
            this.titulos.addAll(profesor.getTitulos().stream()
                    .map(TituloDTO::new).collect(Collectors.toList()));

        this.cursos_id.addAll(profesor.getCursos_dictados().stream().map(Curso::getId).collect(Collectors.toSet()));
    }

    public Set<Integer> getCursos_id() {
        return cursos_id;
    }

    public void setCursos_id(Set<Integer> cursos_id) {
        this.cursos_id = cursos_id;
    }

    public List<CapacitacionDTO> getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(List<CapacitacionDTO> capacitaciones) {
        this.capacitaciones = capacitaciones;
    }

    public List<TituloDTO> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<TituloDTO> titulos) {
        this.titulos = titulos;
    }
}
