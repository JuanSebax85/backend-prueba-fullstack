package com.prueba.backend.controller;

import com.prueba.backend.model.Materia;
import com.prueba.backend.repository.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaRepository repo;

    @GetMapping
    public List<Materia> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Materia crear(@RequestBody Materia m) {
        m.setId(null);
        return repo.save(m);
    }

    @GetMapping("/{id}")
    public Materia obtener(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Materia no encontrada"));
    }

    @PutMapping("/{id}")
    public Materia actualizar(@PathVariable Long id, @RequestBody Materia m) {
        if (!repo.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Materia no encontrada");
        }

        m.setId(id);
        return repo.save(m);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Materia no encontrada");
        }

        repo.deleteById(id);
    }
}