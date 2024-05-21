# Blog App REST API
This project is a Blog Application backend developed using Spring Boot. The application exposes RESTfull APIs for managing blog posts, comments, and user authentication. Below is a comprehensive overview of the technologies and concepts used in this project.

## Features
1. CRUD REST APIs for Blog App
      + CRUD operations for blog posts and comments.
2. One-to-Many Relationship APIs
      + /posts/{postId}/comments/{commentId} endpoint for managing comments on blog posts.
3. Pagination and Sorting
      + APIs supporting pagination and sorting for efficient data retrieval.
4. Search/Filter REST APIs
      + Endpoints for searching and filtering blog posts. 
5. Authentication and Authorization
      + User login and signup functionality.
      + Role-based security using Spring Security.
6. JWT Integration
      + Implementation of JWT (JSON Web Token) for securing APIs.
      + Usage of JWT for login API.
7. API Versioning
      + Four strategies for versioning REST APIs.
8. Exception Handling and Validation
      + Custom exception handling.
      + Request validation.
9. Documentation with Swagger UI
      + API documentation and testing using Swagger UI.
10. Profiles and Transaction Management
      + Profile-specific configurations.
      + Transaction management with Spring Data JPA.
11. Deployment
      + Steps to deploy the application on AWS Cloud (Production environment).
12. Docker Integration
      + Dockerizing the application.
      + Docker Compose setup for running the application with MySQL.

## Technologies Used
+ Spring Boot: Framework for building the application.
+ Spring Security: Security framework for handling authentication and authorization.
+ JWT (JSON Web Token): Used for securing APIs.
+ Spring Data JPA (Hibernate): ORM framework for database operations.
+ MySQL: Relational database used for storing data.
+ Lombok: Library to reduce boilerplate code.
+ DTO (Data Transfer Objects): Used for transferring data between layers.
+ Swagger UI: Tool for API documentation and testing.
+ Postman: Tool for testing REST APIs.
+ Docker: Containerization tool.
+ AWS: Cloud platform for deployment.

Deployment on AWS is currently pending and will be updated in the near future.
