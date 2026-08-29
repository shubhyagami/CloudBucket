# CloudBucket

A lightweight, self‑hosted cloud storage service built with Spring Boot.  
It offers secure authentication, file upload/download (up to 500 MB per file), and a simple dashboard. Ideal for developers who want to experiment with Java web applications and local storage.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Database](https://img.shields.io/badge/Database-H2-blueviolet)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

## Overview

CloudBucket is a Spring Boot application that stores files in a configurable local directory and uses an in‑memory H2 database for development. It includes basic user management, a web dashboard, and ready‑to‑run defaults, making it a convenient sandbox for learning modern Java web development.

## Key Features

- Secure password hashing for authentication  
- Simple user dashboard for file management  
- Upload and download files up to 500 MB each  
- Customizable upload directory via `file.upload-dir`  
- In‑memory H2 database for quick setup and testing  
- Pre‑configured development user (`admin`/`admin`)  

## Getting Started

### Build and run

```bash
# Build the project
./mvnw -DskipTests package

# Start the application
./mvnw spring-boot:run
```

### Access the application

- Signup: http://localhost:8080/signup  
- Login: http://localhost:8080/login  
- Dashboard: http://localhost:8080/dashboard  
- H2 console: http://localhost:8080/h2-console  

## Configuration

Edit `src/main/resources/application.properties` to customize settings.

- **Default development user**
  ```properties
  app.user.username=admin
  app.user.password=admin
  ```

- **File storage location**
  ```properties
  file.upload-dir=/path/to/upload/directory
  ```

## Contributing

1. Fork the repository and create a descriptive branch for your feature or fix.  
2. Run the test suite to verify that existing functionality remains intact.  
3. Add unit tests for any new code you introduce.  
4. Submit a pull request with clean, well‑documented code and no unnecessary debug output.  

## License

CloudBucket is released under the MIT License. See the [LICENSE](LICENSE) file for details.

## Changelog

- **v1.0.0** – Initial release with user management, file upload/download, and H2 integration.  
- **v1.1.0** – Added configurable storage directory and improved error handling.  

---  
Maintained with ❤️ by the CloudBucket maintainer.
