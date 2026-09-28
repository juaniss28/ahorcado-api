package com.example.ahorcadoapi.health;

import com.example.ahorcadoapi.repository.PalabraRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AhorcadoHealthIndicator implements HealthIndicator {

    private final PalabraRepository repository;

    public AhorcadoHealthIndicator(PalabraRepository repository) {
        this.repository = repository;
    }

    @Override
    public Health health() {
        try {
            long cantidadPalabras = repository.count();

            return Health.up()
                    .withDetail("palabrasRegistradas", cantidadPalabras)
                    .build();

        } catch (Exception e) {

            return Health.down()
                    .withDetail("error", "No se pudo consultar la base de datos")
                    .build();
        }
    }
}