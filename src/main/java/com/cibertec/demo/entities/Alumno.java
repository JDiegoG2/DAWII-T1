package com.cibertec.demo.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expediente;

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Date fechaNac;
    private boolean delegado;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModuloAlumno> moduloAlumnos;
}
