package com.example.ahorcadoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "palabras")
public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabra;
    private String categoria;
    private String dificultad;

    protected Palabra() {
    }

    public Palabra(String palabra, String categoria, String dificultad) {
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

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}