package com.prueba.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Nota> notas;

}
