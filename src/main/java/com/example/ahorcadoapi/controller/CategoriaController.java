package com.example.ahorcadoapi.controller;

import com.example.ahorcadoapi.model.Categoria;
import com.example.ahorcadoapi.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(
            @RequestBody Categoria categoria) {

        Categoria nuevaCategoria = repository.save(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        return ResponseEntity.ok(repository.findAll());
    }
}