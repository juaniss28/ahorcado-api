package com.example.ahorcadoapi.dto;

public record PalabraDTO(
        String palabra,
        Long categoriaId,
        String dificultad
) {
}