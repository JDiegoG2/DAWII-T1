package com.cibertec.demo.controller;

import com.cibertec.demo.entities.Modulo;
import com.cibertec.demo.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloRepository moduloRepository;

    // Crear un nuevo Modulo
    @PostMapping
    public Modulo createModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    // Obtener todos los Modulos
    @GetMapping
    public List<Modulo> getAllModulos() {
        return moduloRepository.findAll();
    }

    // Obtener un Modulo por código
    @GetMapping("/{codigo}")
    public ResponseEntity<Modulo> getModuloById(@PathVariable Long codigo) {
        Optional<Modulo> modulo = moduloRepository.findById(codigo);
        if (modulo.isPresent()) {
            return ResponseEntity.ok(modulo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un Modulo
    @PutMapping("/{codigo}")
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

    // Eliminar un Modulo
    @DeleteMapping("/{codigo}")
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
