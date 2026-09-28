package com.example.ahorcadoapi.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class AhorcadoMetrics {

    private final Counter palabrasCreadas;

    public AhorcadoMetrics(MeterRegistry meterRegistry) {
        this.palabrasCreadas = Counter.builder("ahorcado.palabras.creadas")
                .description("Cantidad de palabras creadas en el juego Ahorcado")
                .register(meterRegistry);
    }

    public void incrementarPalabrasCreadas() {
        palabrasCreadas.increment();
    }
}