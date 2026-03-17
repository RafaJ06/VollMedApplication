# VollMed Application
***
![Java](https://img.shields.io/badge/3.4.5-SPRING%20BOOT-6DB33F?style=for-the-badge&logo=springBoot&logoColor=white)
![Java](https://img.shields.io/badge/Java%2017-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL%20Connector-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0202?style=for-the-badge&logo=flyway&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=jsonwebtokens&logoColor=000000&color=d63aff)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-bc2023?style=for-the-badge)
![OpenAPI](https://img.shields.io/badge/Spring%20Doc%20OpenAPI-6BA539?style=for-the-badge&logo=openapiinitiative&logoColor=white)

***
![English](https://img.shields.io/badge/ENGLISH-5b5b5b?style=for-the-badge)

In this repository, I demonstrate how I develop a Java application following the ***Alura Latam + Oracle*** training paths titled:

>Spring Boot 3: Develop a REST API in Java

>Spring Boot 3: Apply Best Practices and Secure a REST API

>Spring Boot 3: Document, Test, and Prepare an API for Deployment

This repository is divided into 3 branches, with each branch representing a specific phase of the project:

***First Branch (Phase 1):*** Creation of RestControllers and validations.

***Second Branch (Phase 2):*** Adding security and login functionality to the project.

***Third Branch (Phase 3):*** Adding documentation, automated/unit tests, and business rules.

## DESCRIPTION
This specific project focuses on implementing a CRUD system for patients and doctors, while developing, understanding, and practicing fundamental concepts of Spring Boot and backend application development.
***

### Key Functionalities:

* Register a patient/doctor [CREATE]

* List one (or multiple) patient(s)/doctor(s) [READ]

* Update a patient/doctor [UPDATE]

* Delete a patient/doctor [DELETE]

>***NOTE:*** Since data deletion is a sensitive matter—especially in healthcare systems—and can pose risks regarding audit trails, we do not perform a physical/hard delete. Instead, we implement a logical delete by changing the entity's status to "INACTIVE."

### Project Achievements

* Implemented a Security Filter by creating a Login for our API.

* Utilized JWT (JSON Web Tokens) as a session validator for our Stateless system.

* Used Flyway for database table versioning and migrations.

* Utilized MySQL as the primary database management system.

* Integrated OpenAPI and Swagger to document our APIs.

* Performed Unit Testing on the core methods of the application.

* Used Maven as the dependency manager.

* Leveraged Spring Data JPA to perform CRUD operations on our entities.

***
![Español](https://img.shields.io/badge/ESPAÑOL-5b5b5b?style=for-the-badge)

 En este repositorio muestro como desarrollo una aplicación Java siguiendo las formaciones de ***Alura Latam + Oracle*** llamadas: 

>Spring Boot 3: desarrolla una API REST en Java

>Spring Boot 3: aplique las mejores prácticas y proteja una API Rest

>Spring Boot 3: documentar, probar y preparar una API para su implementación. 

Este proyecto está dividido en 3 ramas, donde cada rama representa una fase del proyecto:

***Primera rama (fase 1):*** La creación de los RestControllers y validaciones.

***Segunda rama (fase 2):*** Agregamos seguridad y login a nuestro proyecto.

***Tercera rama (fase 3):*** Agregamos documentación, test automatizados/unitarios y algunas reglas de negocio.

## DESCRIPCIÓN
Este proyecto en específico se enfoca en la implementación de un CRUD sobre pacientes y usuarios, además del desarrollo, conocimiento y práctica de conceptos fundamentales en Spring Boot y el desarrollo de aplicaciones BackEnd. 
***

### Podemos: 

- Registrar un paciente/Doctor [CREATE]
- Listar un (o varios) paciente(s)/doctor(es) [READ]
- Actualizar un paciente/doctor [UPDATE]
- Borrar un paciente/Doctor [DELETE] 

> ***OJO:*** Dado que la eliminación de datos es algo delicado (más en un sistema como el de salud), y puede representar ciertos riesgo en temas de auditoría, en vez de realizar una eliminación física/real de los datos realizamos una exclusión lógica cambiando el estado de la entidad a "INACTIVO".

### ¿Qué logramos en este proyecto?

* Pudimos implementar un filtro de seguridad creando un LogIn para nuestra API.

*  Utilizamos JWT que nos sirva de validador de sesión para nuestro sistema Stateless 

* Utilizamos Flyway para versionar las tablas de nuestra BD y las migraciones

* Utilizamos MySQL como gestor de base de datos

* Utilizamos OPEN API y Swagger para documentar nuestras APIS

* Realizamos test unitarios sobre algunos de los métodos principales que creamos.

* Utilizamos Maven con gestor de dependencias

* Utilizamos Spring Data para realizar CRUDs en nuestras entidades.