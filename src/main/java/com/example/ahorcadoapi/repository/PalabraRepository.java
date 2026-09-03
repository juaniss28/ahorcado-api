package com.example.ahorcadoapi.repository;

import com.example.ahorcadoapi.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {

    @Query("SELECT p FROM Palabra p WHERE LOWER(p.categoria) = LOWER(:categoria)")
    List<Palabra> buscarPorCategoria(@Param("categoria") String categoria);
}