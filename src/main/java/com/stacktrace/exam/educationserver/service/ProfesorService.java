package com.stacktrace.exam.educationserver.service;

import com.stacktrace.exam.educationserver.entities.Curso;
import com.stacktrace.exam.educationserver.entities.DTOs.CursoDTO;
import com.stacktrace.exam.educationserver.entities.DTOs.ProfesorDTO;
import com.stacktrace.exam.educationserver.entities.Profesor;
import com.stacktrace.exam.educationserver.repository.CapacitacionRepository;
import com.stacktrace.exam.educationserver.repository.CursoRepository;
import com.stacktrace.exam.educationserver.repository.ProfesorRepository;
import com.stacktrace.exam.educationserver.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    CapacitacionRepository capacitacionRepository;
    @Autowired
    TituloRepository tituloRepository;
    @Autowired
    CursoRepository cursoRepository;

    public ProfesorDTO getProfesor(String dni){
        return new ProfesorDTO(profesorRepository.findByDni(dni));
    }

    public Set<ProfesorDTO> getAll(){
        return profesorRepository.findAll().stream().map(ProfesorDTO::new).collect(Collectors.toSet());
    }

    public ProfesorDTO saveProfesor(ProfesorDTO profesorDTO) {
        //First create professor to relate it to their capacitaciones, titulos
        Profesor profesorReturn = profesorRepository.save(mapProfesorDTOtoProfesorEntity(profesorDTO));

        //Create capacitaciones and titulos related to profesorDTO
        profesorDTO.getCapacitaciones().forEach(capacitacionDTO ->
                capacitacionRepository.save(capacitacionDTO.toCapacitacion(profesorReturn)));
        profesorDTO.getTitulos().forEach(tituloDTO ->
                tituloRepository.save(tituloDTO.toTitulo(profesorReturn)));
        return new ProfesorDTO(profesorReturn);
    }

    @Transactional
    public ProfesorDTO updateProfesor(ProfesorDTO profesorDTO){
        //Clean old capacitaciones, titulos of this profesor
        capacitacionRepository.deleteAllByProfesor_Dni(profesorDTO.getDni());
        tituloRepository.deleteAllByProfesor_Dni(profesorDTO.getDni());

        //Update Profesor with specified titulos and capacitaciones in profesorDTO
        return saveProfesor(profesorDTO);
    }

    @Transactional
    public void removeProfesor(ProfesorDTO profesorDTO){
        //Remove capacitaciones, titulos of this profesor
        capacitacionRepository.deleteAllByProfesor_Dni(profesorDTO.getDni());
        tituloRepository.deleteAllByProfesor_Dni(profesorDTO.getDni());

        //If a professor was associated to a curso, remove it from them
        Set<Curso> cursosOfProfesor = mapProfesorDTOtoProfesorEntity(profesorDTO).getCursos_dictados();
        cursosOfProfesor.forEach(curso -> {
            curso.setDictadoPor(null);
            cursoRepository.save(curso);
        });

        //Remove Profesor
        profesorRepository.deleteById(profesorDTO.getId());
    }

    public List<CursoDTO> getCursosOfProfesor(String profesorDTO_dni){
        Profesor profesor = profesorRepository.findByDni(profesorDTO_dni);
        return profesor.getCursos_dictados().stream()
                .map(CursoDTO::new).collect(Collectors.toList());
    }

    private Profesor mapProfesorDTOtoProfesorEntity(ProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();
        profesor.setId(profesorDTO.getId());
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellido(profesorDTO.getApellido());
        profesor.setDni(profesorDTO.getDni());
        profesor.setDni_tipo(profesorDTO.getDni_tipo());
        profesor.setDomicilio(profesorDTO.getDomicilio());
        profesor.setTelefono(profesorDTO.getTelefono());
        profesor.setFecha_de_nacimiento(profesorDTO.getFecha_de_nacimiento());
        profesor.setSexo(profesorDTO.getSexo());
        mapCursosDictados(profesorDTO, profesor);

        return profesor;
    }

    private void mapCursosDictados(ProfesorDTO profesorDTO, Profesor profesor) {
        if (profesor.getCursos_dictados() == null) {
            profesor.setCursos_dictados(new HashSet<>());
        }

        profesorDTO.getCursos_id().forEach(cursoID -> {
            cursoRepository.findById(cursoID).ifPresent(
                    curso -> profesor.getCursos_dictados().add(curso)
            );
        });
    }

}