package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Alumno;
import com.cibertec.demo.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Crear un nuevo Alumno
    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // Obtener todos los Alumnos
    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    // Obtener un Alumno por expediente
    @GetMapping("/{expediente}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long expediente) {
        Optional<Alumno> alumno = alumnoRepository.findById(expediente);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un Alumno
    @PutMapping("/{expediente}")
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

    // Eliminar un Alumno
    @DeleteMapping("/{expediente}")
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
