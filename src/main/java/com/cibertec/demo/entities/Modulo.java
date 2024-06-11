package com.cibertec.demo.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "rfc_profesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuloAlumno> moduloAlumnos;
}