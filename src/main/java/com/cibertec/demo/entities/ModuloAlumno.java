package com.cibertec.demo.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idModuloAlumno")
public class ModuloAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModuloAlumno;

    @ManyToOne
    @JoinColumn(name = "expediente")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "codigo")
    private Modulo modulo;
}
