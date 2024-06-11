package com.cibertec.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Profesor {
    @Id
    @Column(name = "rfc", length = 15)
    private String rfc;

    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String direccion;
    @Column(length = 10)
    private String telefono;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modulo> modulos;
}
