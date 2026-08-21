# CloudBucket

```
   ___ _                 _    ____            _    _   
  / __| |_  ___ __ _  __| |__| __ ) _   _  __| |_ | |_ 
 | (__| ' \/ -_) _` |/ _` / _` | _ \ | | |/ _` | ' \ 
  \___|_||_\___\__,_|\__,_\__,_|___/ \_,_|\__,_|_||_|
```

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Database](https://img.shields.io/badge/Database-H2-blueviolet)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)
[![Maintained](https://img.shields.io/badge/Maintained-Yes-success)](https://github.com/shubhyagami/CloudBucket)

CloudBucket is a minimal Spring Boot application that simulates a personal cloud storage experience. It serves as a practical reference for core web development concepts, featuring user authentication, file management, and database integration.

## Features

- **Authentication:** Secure signup and login flows powered by Spring Security.
- **User Dashboard:** An isolated workspace where users only see and manage their own uploaded files.
- **File Management:** Upload files up to 500 MB and download previously uploaded files.
- **Configurable Local Storage:** Uploaded files are saved to a local directory specified in the application properties.
- **In-Memory Database:** Powered by H2, making setup and development effortless.

## Getting Started

To get a local copy up and running, you only need Java 21 and Maven (the project includes a Maven wrapper).

### Build & Run

1. Build the project from the root directory:
   ```bash
   ./mvnw -DskipTests package
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Open the application in your browser:
   - **Signup:** `http://localhost:8080/signup`
   - **Login:** `http://localhost:8080/login`
   - **Dashboard:** `http://localhost:8080/dashboard`
   - **H2 Console:** `http://localhost:8080/h2-console`

## Configuration

All application settings are managed in `src/main/resources/application.properties`.

### Default Credentials

A default development user is provided for quick testing:
- **Username:** `admin`
- **Password:** `admin`

To create additional users, use the signup page. 

### File Upload Directory

Customize the local storage path for uploaded files by modifying the `file.upload-dir` property in `application.properties`.

## Changelog

### [1.0.0] - Initial Release
- Implemented secure user authentication.
- Added user-specific dashboard and isolated file storage.
- Integrated H2 database for local development.
- Updated Spring Boot to 3.4.1.
- Refined README structure and documentation.

## Contributing

Contributions are welcome! To keep the project clean and maintainable, please follow these guidelines:

1. **Check existing issues:** Search the issue tracker to avoid duplicate submissions.
2. **Branching:** Create a descriptive branch (e.g., `feature/add-file-preview` or `fix/login-redirect`).
3. **Testing:** Ensure all new features or bug fixes include appropriate unit tests.
4. **Pull Requests:** Submit a PR for review. Ensure your code follows the existing style (Java 21, Spring Boot 3.4.x, 4-space indentation) and includes no leftover debug logs or unused imports.

Contributors will be recognized in the `CONTRIBUTORS.md` file.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
