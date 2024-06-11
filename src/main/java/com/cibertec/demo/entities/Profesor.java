package com.cibertec.demo.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rfc")
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
    @JsonIgnore
    private List<Modulo> modulos;
}
