package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Modulo;
import com.cibertec.demo.repositories.ModuloRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modulos")
@Tag(name = "Modulo", description = "API para la gestión de Módulos")
public class ModuloController {

    @Autowired
    private ModuloRepository moduloRepository;

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo Modulo")
    public Modulo createModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los Modulos")
    public List<Modulo> getAllModulos() {
        return moduloRepository.findAll();
    }

    @GetMapping("/get/{codigo}")
    @Operation(summary = "Obtener un Modulo por código")
    public ResponseEntity<Modulo> getModuloById(@PathVariable Long codigo) {
        Optional<Modulo> modulo = moduloRepository.findById(codigo);
        if (modulo.isPresent()) {
            return ResponseEntity.ok(modulo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{codigo}")
    @Operation(summary = "Actualizar un Modulo")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Long codigo, @RequestBody Modulo moduloDetails) {
        Optional<Modulo> optionalModulo = moduloRepository.findById(codigo);
        if (optionalModulo.isPresent()) {
            Modulo modulo = optionalModulo.get();
            modulo.setNombre(moduloDetails.getNombre());
            Modulo updatedModulo = moduloRepository.save(modulo);
            return ResponseEntity.ok(updatedModulo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{codigo}")
    @Operation(summary = "Eliminar un Modulo")
    public ResponseEntity<Void> deleteModulo(@PathVariable Long codigo) {
        Optional<Modulo> optionalModulo = moduloRepository.findById(codigo);
        if (optionalModulo.isPresent()) {
            moduloRepository.delete(optionalModulo.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
