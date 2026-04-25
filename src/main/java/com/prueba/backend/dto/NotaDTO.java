package com.prueba.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotaDTO {

    private Long id;

    private Double valor;

    private Long alumnoId;
    private String alumnoNombre;
    private String alumnoApellido;

    private Long materiaId;
    private String materiaNombre;
}