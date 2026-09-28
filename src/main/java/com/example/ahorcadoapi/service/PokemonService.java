package com.example.ahorcadoapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PokemonService {

    private final RestClient restClient;

    public PokemonService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }

    public String obtenerPokemon(String nombre) {
        return restClient.get()
                .uri("/pokemon/{nombre}", nombre)
                .retrieve()
                .body(String.class);
    }
}