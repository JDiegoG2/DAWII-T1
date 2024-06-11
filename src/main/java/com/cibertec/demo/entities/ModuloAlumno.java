package com.cibertec.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ModuloAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModuloAlumno;

    @ManyToOne
    @JoinColumn(name = "codigo_alumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "codigo_modulo")
    private Modulo modulo;
}
