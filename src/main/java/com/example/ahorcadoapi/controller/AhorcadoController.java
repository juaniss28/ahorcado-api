package com.example.ahorcadoapi.controller;

import com.example.ahorcadoapi.dto.PalabraDTO;
import com.example.ahorcadoapi.model.Categoria;
import com.example.ahorcadoapi.model.Palabra;
import com.example.ahorcadoapi.repository.CategoriaRepository;
import com.example.ahorcadoapi.repository.PalabraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ahorcadoapi.observability.AhorcadoMetrics;

import java.util.List;

@RestController
@RequestMapping("/ahorcado")
public class AhorcadoController {

    private final PalabraRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final AhorcadoMetrics ahorcadoMetrics;


    public AhorcadoController(
        PalabraRepository repository,
        CategoriaRepository categoriaRepository,
        AhorcadoMetrics ahorcadoMetrics) {

    this.repository = repository;
    this.categoriaRepository = categoriaRepository;
    this.ahorcadoMetrics = ahorcadoMetrics;
}

    // GET: obtener todas las palabras
    @GetMapping
    public ResponseEntity<List<Palabra>> obtenerPalabras() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET + PathVariable: obtener una palabra por ID
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> obtenerPalabraPorId(
            @PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET + RequestParam: buscar por categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<Palabra>> buscarPorCategoria(
            @RequestParam String categoria) {

        return ResponseEntity.ok(
                repository.buscarPorCategoria(categoria)
        );
    }

    // POST: crear una palabra
    @PostMapping
    public ResponseEntity<?> crearPalabra(
            @RequestBody PalabraDTO datos) {

        Categoria categoria = categoriaRepository
                .findById(datos.categoriaId())
                .orElse(null);

        if (categoria == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("La categoría no existe");
        }

        Palabra nuevaPalabra = new Palabra(
                datos.palabra(),
                categoria,
                datos.dificultad()
        );

        Palabra palabraGuardada = repository.save(nuevaPalabra);
        ahorcadoMetrics.incrementarPalabrasCreadas();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(palabraGuardada);
    }

    // PUT: actualizar una palabra
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPalabra(
            @PathVariable Long id,
            @RequestBody PalabraDTO datos) {

        Palabra palabra = repository.findById(id).orElse(null);

        if (palabra == null) {
            return ResponseEntity.notFound().build();
        }

        Categoria categoria = categoriaRepository
                .findById(datos.categoriaId())
                .orElse(null);

        if (categoria == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("La categoría no existe");
        }

        palabra.setPalabra(datos.palabra());
        palabra.setCategoria(categoria);
        palabra.setDificultad(datos.dificultad());

        Palabra actualizada = repository.save(palabra);

        return ResponseEntity.ok(actualizada);
    }

    // DELETE: eliminar una palabra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPalabra(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}