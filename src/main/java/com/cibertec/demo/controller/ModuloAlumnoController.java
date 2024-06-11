package com.cibertec.demo.controller;


import com.cibertec.demo.entities.ModuloAlumno;
import com.cibertec.demo.repositories.ModuloAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moduloalumnos")
public class ModuloAlumnoController {

    @Autowired
    private ModuloAlumnoRepository moduloAlumnoRepository;

    // Crear un nuevo ModuloAlumno
    @PostMapping
    public ModuloAlumno createModuloAlumno(@RequestBody ModuloAlumno moduloAlumno) {
        return moduloAlumnoRepository.save(moduloAlumno);
    }

    // Obtener todos los ModuloAlumnos
    @GetMapping
    public List<ModuloAlumno> getAllModuloAlumnos() {
        return moduloAlumnoRepository.findAll();
    }

    // Obtener un ModuloAlumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<ModuloAlumno> getModuloAlumnoById(@PathVariable Long id) {
        Optional<ModuloAlumno> moduloAlumno = moduloAlumnoRepository.findById(id);
        return moduloAlumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un ModuloAlumno
    @PutMapping("/{id}")
    public ResponseEntity<ModuloAlumno> updateModuloAlumno(@PathVariable Long id, @RequestBody ModuloAlumno moduloAlumnoDetails) {
        return moduloAlumnoRepository.findById(id)
                .map(moduloAlumno -> {
                    moduloAlumno.setAlumno(moduloAlumnoDetails.getAlumno());
                    moduloAlumno.setModulo(moduloAlumnoDetails.getModulo());
                    ModuloAlumno updatedModuloAlumno = moduloAlumnoRepository.save(moduloAlumno);
                    return ResponseEntity.ok(updatedModuloAlumno);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un ModuloAlumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteModuloAlumno(@PathVariable Long id) {
        return moduloAlumnoRepository.findById(id)
                .map(moduloAlumno -> {
                    moduloAlumnoRepository.delete(moduloAlumno);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
