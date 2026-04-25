package com.prueba.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String codigo;
    private Integer creditos;

    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<Nota> notas;
}