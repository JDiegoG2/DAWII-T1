package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Alumno;
import com.cibertec.demo.repositories.AlumnoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumno", description = "API para la gestión de Alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo Alumno")
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los Alumnos")
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @GetMapping("/get/{expediente}")
    @Operation(summary = "Obtener un Alumno por expediente")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long expediente) {
        Optional<Alumno> alumno = alumnoRepository.findById(expediente);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{expediente}")
    @Operation(summary = "Actualizar un Alumno")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long expediente, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(expediente);
        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            alumno.setNombre(alumnoDetails.getNombre());
            alumno.setApellidoP(alumnoDetails.getApellidoP());
            alumno.setApellidoM(alumnoDetails.getApellidoM());
            alumno.setFechaNac(alumnoDetails.getFechaNac());
            alumno.setDelegado(alumnoDetails.isDelegado());
            Alumno updatedAlumno = alumnoRepository.save(alumno);
            return ResponseEntity.ok(updatedAlumno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{expediente}")
    @Operation(summary = "Eliminar un Alumno")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long expediente) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(expediente);
        if (optionalAlumno.isPresent()) {
            alumnoRepository.delete(optionalAlumno.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
