package com.prueba.backend.controller;

import com.prueba.backend.model.Alumno;
import com.prueba.backend.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoRepository repo;

    @GetMapping
    public List<Alumno> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Alumno crear(@RequestBody Alumno a) {
        a.setId(null);
        return repo.save(a);
    }

    @GetMapping("/{id}")
    public Alumno obtener(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alumno no encontrado"));
    }

    @PutMapping("/{id}")
    public Alumno actualizar(@PathVariable Long id, @RequestBody Alumno a) {
        if (!repo.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Alumno no encontrado");
        }

        a.setId(id);
        return repo.save(a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Alumno no encontrado");
        }

        repo.deleteById(id);
    }
}