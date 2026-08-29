package com.example.ahorcadoapi.model;

public class Palabra {

    private Long id;
    private String palabra;
    private String categoria;
    private String dificultad;

    public Palabra(Long id, String palabra, String categoria, String dificultad) {
        this.id = id;
        this.palabra = palabra;
        this.categoria = categoria;
        this.dificultad = dificultad;
    }

    public Long getId() {
        return id;
    }

    public String getPalabra() {
        return palabra;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDificultad() {
        return dificultad;
    }
}