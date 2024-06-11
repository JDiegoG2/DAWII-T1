package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Profesor;
import com.cibertec.demo.repositories.ProfesorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
@Tag(name = "Profesor", description = "API para la gestión de Profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo Profesor")
    public Profesor createProfesor(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los Profesores")
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    @GetMapping("/get/{rfc}")
    @Operation(summary = "Obtener un Profesor por RFC")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable String rfc) {
        Optional<Profesor> profesor = profesorRepository.findById(rfc);
        if (profesor.isPresent()) {
            return ResponseEntity.ok(profesor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{rfc}")
    @Operation(summary = "Actualizar un Profesor")
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

    @DeleteMapping("/delete/{rfc}")
    @Operation(summary = "Eliminar un Profesor")
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
