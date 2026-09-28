package com.example.ahorcadoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "palabras")
public class Palabra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabra;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String dificultad;

    protected Palabra() {
    }

    public Palabra(String palabra, Categoria categoria, String dificultad) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

        public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}