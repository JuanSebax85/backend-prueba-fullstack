package com.prueba.backend.controller;

import com.prueba.backend.dto.NotaDTO;
import com.prueba.backend.model.Nota;
import com.prueba.backend.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaRepository repo;

    @GetMapping
    public List<NotaDTO> listar() {
        return repo.findAll()
                .stream()
                .filter(n -> n.getAlumno() != null && n.getMateria() != null)
                .map(n -> new NotaDTO(
                        n.getId(),
                        n.getValor(),
                        n.getAlumno().getId(),
                        n.getAlumno().getNombre(),
                        n.getAlumno().getApellido(),
                        n.getMateria().getId(),
                        n.getMateria().getNombre()))
                .toList();
    }

    @PostMapping
    public Nota crear(@RequestBody Nota n) {
        return repo.save(n);
    }

    @GetMapping("/{id}")
    public NotaDTO obtener(@PathVariable Long id) {
        Nota n = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

        return new NotaDTO(
                n.getId(),
                n.getValor(),
                n.getAlumno().getId(),
                n.getAlumno().getNombre(),
                n.getAlumno().getApellido(),
                n.getMateria().getId(),
                n.getMateria().getNombre());
    }

    @PutMapping("/{id}")
    public Nota actualizar(@PathVariable Long id, @RequestBody Nota n) {

        Nota existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

        existente.setValor(n.getValor());

        existente.setAlumno(n.getAlumno());
        existente.setMateria(n.getMateria());

        return repo.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Nota no encontrada");
        }
        repo.deleteById(id);
    }
}