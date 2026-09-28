package com.example.ahorcadoapi.controller;

import com.example.ahorcadoapi.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/ahorcado/pokemon")
public class PokemonController {

    private static final Logger logger =
            LoggerFactory.getLogger(PokemonController.class);

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerPokemon(
            @PathVariable String nombre) {

        try {
            logger.info("Consultando Pokemon: {}", nombre);

            String respuesta = pokemonService.obtenerPokemon(nombre);

            return ResponseEntity.ok(respuesta);

        } catch (RestClientException e) {

            logger.error("Error al consultar Pokemon: {}", nombre, e);

            return ResponseEntity
                    .status(502)
                    .body("No fue posible consultar la API externa");
        }
    }
}