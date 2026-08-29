package com.example.ahorcadoapi.controller;

import com.example.ahorcadoapi.dto.PalabraDTO;
import com.example.ahorcadoapi.model.Palabra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AhorcadoController {

    private List<Palabra> palabras = new ArrayList<>(Arrays.asList(
            new Palabra(1L, "elefante", "animales", "facil"),
            new Palabra(2L, "computadora", "tecnologia", "media"),
            new Palabra(3L, "mariposa", "animales", "facil"),
            new Palabra(4L, "programacion", "tecnologia", "dificil")
    ));

    // GET: obtener todas las palabras
    @GetMapping("/ahorcado")
    public ResponseEntity<List<Palabra>> obtenerPalabras() {
        return ResponseEntity.ok(palabras);
    }

    // GET + PathVariable: obtener una palabra por ID
    @GetMapping("/ahorcado/{id}")
    public ResponseEntity<Palabra> obtenerPalabraPorId(@PathVariable Long id) {

        for (Palabra palabra : palabras) {
            if (palabra.getId().equals(id)) {
                return ResponseEntity.ok(palabra);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET + RequestParam: buscar por categoría
    @GetMapping("/ahorcado/buscar")
    public ResponseEntity<List<Palabra>> buscarPorCategoria(
            @RequestParam String categoria) {

        List<Palabra> resultados = new ArrayList<>();

        for (Palabra palabra : palabras) {
            if (palabra.getCategoria().equalsIgnoreCase(categoria)) {
                resultados.add(palabra);
            }
        }

        return ResponseEntity.ok(resultados);
    }

    // POST + RequestBody + DTO
    @PostMapping("/ahorcado")
    public ResponseEntity<Palabra> crearPalabra(
            @RequestBody PalabraDTO datos) {

        Long nuevoId = palabras.size() + 1L;

        Palabra nuevaPalabra = new Palabra(
                nuevoId,
                datos.palabra(),
                datos.categoria(),
                datos.dificultad()
        );

        palabras.add(nuevaPalabra);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaPalabra);
    }
}