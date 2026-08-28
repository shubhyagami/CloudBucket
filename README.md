# CloudBucket

A personal cloud storage service built with Spring Boot, offering secure authentication, file upload/download (up to 500 MB), and a lightweight dashboard.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Database](https://img.shields.io/badge/Database-H2-blueviolet)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

## Overview

CloudBucket provides a simple, self‑hosted cloud storage solution powered by Spring Boot and an in‑memory H2 database. It is ideal for developers who want a quick, extensible way to store files locally while learning modern Java web development.

## Key Features

- **Secure authentication** with password hashing  
- **User dashboard** for managing files  
- **File upload/download** (max 500 MB per file)  
- **Configurable local storage directory** via `file.upload-dir`  
- **In‑memory H2 database** for development and testing  
- **Pre‑configured development user** (`admin/admin`)  

## Getting Started

### Build & Run

```bash
# Build the project
./mvnw -DskipTests package

# Run the application
./mvnw spring-boot:run
```

### Access

- **Signup**: http://localhost:8080/signup  
- **Login**: http://localhost:8080/login  
- **Dashboard**: http://localhost:8080/dashboard  
- **H2 Console**: http://localhost:8080/h2-console  

## Configuration

Edit `src/main/resources/application.properties` to customize:

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

1. **Fork** the repository and create a descriptive branch for your work.  
2. **Run tests** to ensure new code passes existing coverage.  
3. **Add unit tests** for any new functionality.  
4. **Submit a Pull Request** with clean code and minimal debug output.  

## License

CloudBucket is released under the MIT License. See the [LICENSE](LICENSE) file for details.

## Changelog

- **v1.0.0** – Initial release with user management, file upload/download, and H2 integration.  
- **v1.1.0** – Added configurable storage directory and improved error handling.  

---  

*Built with ❤️ by the CloudBucket maintainer.*
