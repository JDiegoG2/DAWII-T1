package com.cibertec.demo.controller;

import com.cibertec.demo.entities.ModuloAlumno;
import com.cibertec.demo.repositories.ModuloAlumnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moduloalumnos")
@Tag(name = "ModuloAlumno", description = "API para la gestión de ModuloAlumno")
public class ModuloAlumnoController {

    @Autowired
    private ModuloAlumnoRepository moduloAlumnoRepository;

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo ModuloAlumno")
    public ModuloAlumno createModuloAlumno(@RequestBody ModuloAlumno moduloAlumno) {
        return moduloAlumnoRepository.save(moduloAlumno);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los ModuloAlumnos")
    public List<ModuloAlumno> getAllModuloAlumnos() {
        return moduloAlumnoRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un ModuloAlumno por ID")
    public ResponseEntity<ModuloAlumno> getModuloAlumnoById(@PathVariable Long id) {
        Optional<ModuloAlumno> moduloAlumno = moduloAlumnoRepository.findById(id);
        return moduloAlumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar un ModuloAlumno")
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

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un ModuloAlumno")
    public ResponseEntity<Object> deleteModuloAlumno(@PathVariable Long id) {
        return moduloAlumnoRepository.findById(id)
                .map(moduloAlumno -> {
                    moduloAlumnoRepository.delete(moduloAlumno);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
