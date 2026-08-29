# API REST - Juego del Ahorcado

API REST básica desarrollada con Java 17 y Spring Boot para gestionar palabras de un juego de Ahorcado.

## Descripción

El proyecto permite consultar palabras, buscar palabras por categoría y registrar nuevas palabras mediante diferentes endpoints REST.

## Tecnologías

- Java 17
- Spring Boot
- Maven
- JSON
- API REST

## Endpoints

### Obtener todas las palabras

GET /ahorcado

### Obtener una palabra por ID

GET /ahorcado/{id}

### Buscar por categoría

GET /ahorcado/buscar?categoria=animales

### Crear una nueva palabra

POST /ahorcado

Ejemplo de JSON:

{
  "palabra": "dragon",
  "categoria": "animales",
  "dificultad": "media"
}

## Ejecución

Para ejecutar la aplicación:

./mvnw spring-boot:run

La API estará disponible en:

http://localhost:8080
