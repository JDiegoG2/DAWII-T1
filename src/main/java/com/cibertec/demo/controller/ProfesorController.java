package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Profesor;
import com.cibertec.demo.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    // Crear un nuevo Profesor
    @PostMapping
    public Profesor createProfesor(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    // Obtener todos los Profesores
    @GetMapping
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    // Obtener un Profesor por RFC
    @GetMapping("/{rfc}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable String rfc) {
        Optional<Profesor> profesor = profesorRepository.findById(rfc);
        if (profesor.isPresent()) {
            return ResponseEntity.ok(profesor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un Profesor
    @PutMapping("/{rfc}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable String rfc, @RequestBody Profesor profesorDetails) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(rfc);
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            profesor.setNombre(profesorDetails.getNombre());
            profesor.setApellidoP(profesorDetails.getApellidoP());
            profesor.setApellidoM(profesorDetails.getApellidoM());
            profesor.setDireccion(profesorDetails.getDireccion());
            profesor.setTelefono(profesorDetails.getTelefono());
            Profesor updatedProfesor = profesorRepository.save(profesor);
            return ResponseEntity.ok(updatedProfesor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un Profesor
    @DeleteMapping("/{rfc}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable String rfc) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(rfc);
        if (optionalProfesor.isPresent()) {
            profesorRepository.delete(optionalProfesor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
