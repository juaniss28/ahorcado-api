# API REST - Juego del Ahorcado

API REST desarrollada con Java 17 y Spring Boot para gestionar palabras de un juego de Ahorcado.

El proyecto permite almacenar palabras y categorías en MySQL, consultar y modificar información mediante endpoints REST, consumir una API externa y utilizar herramientas de observabilidad para monitorear el funcionamiento de la aplicación.

## Integrantes

- Juanita Oliveros

## Descripción

La aplicación permite gestionar palabras del juego del Ahorcado.

Las palabras están relacionadas con categorías mediante una relación `ManyToOne`. La información se almacena de forma persistente en una base de datos MySQL utilizando Spring Data JPA e Hibernate.

Además, la aplicación consume la API pública PokeAPI como servicio externo y maneja los errores que pueden presentarse durante la comunicación.

El proyecto también incorpora herramientas de observabilidad mediante Spring Boot Actuator, Micrometer y Prometheus.

## Tecnologías

- Java 17
- Spring Boot 4.1.1
- Maven
- Spring Data JPA
- Hibernate
- MySQL 8
- REST API
- JSON
- RestClient
- Spring Boot Actuator
- Micrometer
- Prometheus
- Git y GitHub

## Estructura principal

El proyecto está organizado principalmente en:

- `controller`: contiene los controladores REST.
- `model`: contiene las entidades `Palabra` y `Categoria`.
- `repository`: contiene los repositorios JPA.
- `dto`: contiene los objetos de transferencia de datos.
- `service`: contiene la lógica para consumir la API externa.
- `observability`: contiene la métrica personalizada.
- `health`: contiene el indicador de salud personalizado.

## Persistencia con MySQL

La aplicación utiliza MySQL como base de datos.

Base de datos utilizada:

```text
ahorcado_db