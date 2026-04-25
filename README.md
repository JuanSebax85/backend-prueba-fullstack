# Backend Prueba Técnica 

Este proyecto es una API REST desarrollada con Spring Boot, conectada a una base de datos PostgreSQL 
y completamente dockerizada para su ejecución en cualquier entorno sin configuración adicional.

# Tecnologías utilizadas
Java 17
Spring Boot 3
Spring Data JPA
PostgreSQL
Docker & Docker Compose
Maven
Configuración de variables de entorno

# El proyecto utiliza las siguientes variables de entorno:

DB_URI=jdbc:postgresql://db:5432/prueba
DB_USER=postgres
DB_PASSWORD=1234
DB_DRIVER=org.postgresql.Driver

# --------------------------Ejecución del proyecto------------------------------
Una vez clonado el repositorio, desde la raiz del proyecto ejecutar:
1. Levantar la base de datos:
  docker compose up -d db

2. Restaurar la base de datos:
   docker cp backup.dump postgres_db:/backup.dump
   docker exec -it postgres_db psql -U postgres -d prueba -f /backup.dump

3. Levantar el backend:
   docker compose up --build backend

# --------------------------Pruebas de la API------------------------------------
Una vez el proyecto esté en ejecución, la API estará disponible con los siguientes endpoints listos para ser probados en POSTMAN o servicios similares:

Alumnos
GET http://localhost:8080/alumnos
POST /alumnos
GET  /alumnos/{id}
PUT  /alumnos/{id}
DELETE /alumnos/{id}

Materias
GET http://localhost:8080/materias
POST  /materias
GET   /materias/{id}
PUT   /materias/{id}
DELETE /materias/{id}

Notas
GET http://localhost:8080/notas
POST /notas
GET  /notas/{id}
PUT  /notas/{id}
DELETE /notas/{id}

# ---------------------IMPORTANTE---------------------------------
{id} representa el identificador del recurso en la base de datos.
En Postman debe reemplazarse por un valor real según los datos existentes.

Ejemplo:
DELETE http://localhost:8080/alumnos/1

Sobre eliminación de alumnos y materias

No es posible eliminar directamente un alumno o una materia que tenga notas asociadas en la base de datos debido a la integridad referencial entre las entidades.

La entidad Nota contiene dos claves foráneas (alumno_id y materia_id) que referencian al alumno y a la materia respectivamente. Por esta razón, la base de datos evita la eliminación de estas entidades mientras existan registros dependientes, con el fin de prevenir inconsistencias.

Para eliminar un alumno o una materia correctamente, primero deben eliminarse todas las notas asociadas a dichas entidades y posteriormente realizar la eliminación.

Ejemplo:

Eliminar notas asociadas
DELETE /notas/{id}

Eliminar alumno o materia
DELETE /alumnos/{id}
DELETE /materias/{id}
