# Learning Navigator Application

A Spring Boot-based RESTful API for managing exam enrollments in a Learning Management System (LMS), using MySQL for persistence.

## Features

**CRUD Operations**: Manage Students, Subjects, and Exams.
**Enrollment Rules**: Enroll students in exams only after enrolling in related subjects.
**Global Exception Handling**: Centralized error management via @ControllerAdvice.

## Installation and Usage
### Prerequisites
* Java 17 or higher
* Spring Boot 2.x or higher
* Postman for API testing

### Getting Started

1. **Clone the repository**:

    ```bash
    git clone https://github.com/UdayNarayan95/learning-navigator.git
    ```

2. **Navigate to the project directory**:

    ```bash
    cd learning-navigator
    ```

3. **Build and run the application using Gradle**:

    ```bash
    ./gradlew bootrun
    ```

4. **Configuration**
The application uses Spring Boot's auto-configuration feature. You can customize the application settings by creating a application.properties file in the src/main/resources directory.



### API Testing

For testing the API endpoints, you can use the following Postman Collection:

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/19032644-39e44d7d-a926-4d51-b5e2-1cf71a680aa0?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D19032644-39e44d7d-a926-4d51-b5e2-1cf71a680aa0%26entityType%3Dcollection%26workspaceId%3D8ffcea5c-fb17-4ea1-a212-023358a283e2)  


