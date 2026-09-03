package com.example.ahorcadoapi.controller;

import com.example.ahorcadoapi.dto.PalabraDTO;
import com.example.ahorcadoapi.model.Palabra;
import com.example.ahorcadoapi.repository.PalabraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ahorcado")
public class AhorcadoController {

    private final PalabraRepository repository;

    public AhorcadoController(PalabraRepository repository) {
        this.repository = repository;
    }

    // GET: obtener todas las palabras
    @GetMapping
    public ResponseEntity<List<Palabra>> obtenerPalabras() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET + PathVariable: obtener una palabra por ID
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> obtenerPalabraPorId(@PathVariable Long id) {

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

    // POST + RequestBody + DTO
    @PostMapping
    public ResponseEntity<Palabra> crearPalabra(
            @RequestBody PalabraDTO datos) {

        Palabra nuevaPalabra = new Palabra(
                datos.palabra(),
                datos.categoria(),
                datos.dificultad()
        );

        Palabra palabraGuardada = repository.save(nuevaPalabra);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(palabraGuardada);
    }

    // PUT: actualizar una palabra
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> actualizarPalabra(
            @PathVariable Long id,
            @RequestBody PalabraDTO datos) {

        return repository.findById(id)
                .map(palabra -> {

                    palabra.setPalabra(datos.palabra());
                    palabra.setCategoria(datos.categoria());
                    palabra.setDificultad(datos.dificultad());

                    Palabra actualizada = repository.save(palabra);

                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una palabra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPalabra(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}