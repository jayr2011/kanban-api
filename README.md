# Kanban API

A simple Kanban board API built with Spring Boot.

## Features
- User management (create, list, get by ID)
- H2 database integration (file-based)
- RESTful endpoints
- Password encryption with Jasypt
- Validation with Jakarta Bean Validation
- Logging with Log4j2

## Technologies
- Java 19
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Log4j2
- Jasypt
- Lombok
- Docker

## Getting Started

### Prerequisites
- Java 19+
- Maven
### Running the Project
1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd kanban-api
   ```
2. Build and run:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the H2 console at [http://localhost:8080/h2](http://localhost:8080/h2)

### API Endpoints

| Method | Endpoint           | Description           |
|--------|--------------------|----------------------|
| GET    | /sec/users         | List all users       |
| GET    | /sec/users/{id}    | Get user by ID       |
| POST   | /sec/addUser       | Create a new user    |

> **Note:** Endpoints are prefixed with `/sec` by default.

### Example: Create User
```json
POST /sec/addUser
{
  "name": "John Doe",
  "email": "john@example.com",
  "documentNumber": 123456789,
  "password": "yourpassword"
}
```

## Configuration
Main settings are in `src/main/resources/application.properties`:
- H2 database file: `./data/kanbandb`
- H2 console enabled
- Jasypt encryption password

## License
MIT

---
Developed by Jair Santos
