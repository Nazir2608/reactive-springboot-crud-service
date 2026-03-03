# reactive-springboot-crud-service

A production-ready **Spring Boot WebFlux** + **Reactive MongoDB** CRUD service with Swagger/OpenAPI docs.

## Tech Stack

- Java 21
- Spring Boot 3.2.0
- Spring WebFlux (Project Reactor)
- Spring Data MongoDB Reactive
- SpringDoc OpenAPI 2.3.0 (Swagger UI)
- Lombok
- JUnit 5 + Reactor StepVerifier

---

## Project Structure

```
reactive-springboot-crud-service/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/reactivecrud/
    │   │   ├── ReactiveApplication.java
    │   │   ├── controller/ProductController.java
    │   │   ├── service/ProductService.java
    │   │   ├── repository/ProductRepository.java
    │   │   ├── model/Product.java
    │   │   ├── dto/ProductRequest.java
    │   │   ├── config/SwaggerConfig.java
    │   │   └── exception/
    │   │       ├── ProductNotFoundException.java
    │   │       └── GlobalExceptionHandler.java
    │   └── resources/application.yml
    └── test/
        └── java/com/example/reactivecrud/ProductServiceTest.java
```

---

## Prerequisites

- Java 17+
- Maven 3.8+
- MongoDB running on `localhost:27017`

---

## Run the Application

```bash
# 1. Start MongoDB
mongod --dbpath /data/db

# 2. Build and Run
./mvnw spring-boot:run
```

---

## API Endpoints

| Method   | Endpoint                          | Description           |
|----------|-----------------------------------|-----------------------|
| POST     | /api/v1/products                  | Create product        |
| GET      | /api/v1/products                  | Get all products      |
| GET      | /api/v1/products/{id}             | Get by ID             |
| GET      | /api/v1/products/category/{cat}   | Get by category       |
| GET      | /api/v1/products/search?name=     | Search by name        |
| PUT      | /api/v1/products/{id}             | Update product        |
| DELETE   | /api/v1/products/{id}             | Delete by ID          |
| DELETE   | /api/v1/products                  | Delete all            |

---

## Swagger UI
http://localhost:8080/swagger-ui.html

## OpenAPI Docs (JSON)
http://localhost:8080/api-docs

---

## Sample Request Body

POST /api/v1/products

```json
{
  "name": "MacBook Pro",
  "description": "Apple M3 Chip Laptop",
  "price": 1999.99,
  "quantity": 50,
  "category": "Electronics"
}
```

---

## Run Tests

```bash
./mvnw test
```

---

## Key Reactive Concepts

- Mono - Single async result (create, get, update, delete)
- Flux - Stream of results (get all, search, filter)
- switchIfEmpty() - Throw 404 when document not found
- flatMap() - Chain async DB operations
- ReactiveMongoRepository - Non-blocking MongoDB driver
- StepVerifier - Reactor unit testing tool