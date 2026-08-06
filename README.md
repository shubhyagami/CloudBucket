# CloudBucket

```
   ___ _                 _    ____            _    _   
  / __| |_  ___ __ _  __| |__| __ ) _   _  __| |_ | |_ 
 | (__| ' \/ -_) _` |/ _` / _` | _ \| | | |/ _` | ' \ 
  \___|_||_\___\__,_|\__,_\__,_|___/ \_,_|\__,_|_||_|
```

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)
[![Maintained](https://img.shields.io/badge/Maintained-Yes-success)](https://github.com/shubhyagami/CloudBucket)

**CloudBucket** is a minimal Spring Boot application that provides a simple personal cloud storage experience. It demonstrates core web development concepts including user authentication, file uploads, and database management.

## Features

- **User Authentication:** Secure signup and login powered by Spring Security.
- **User Dashboard:** An isolated view showing only the files uploaded by the authenticated user.
- **File Management:** Upload single files (up to 500 MB) and download previously uploaded files.
- **Local Storage:** Files are saved to a configurable local directory.
- **In-Memory Database:** H2 database for easy setup and development.

## Getting Started

### Prerequisites

- Java 21
- Maven (wrapper is included)

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
   - **Signup:** http://localhost:8080/signup
   - **Login:** http://localhost:8080/login
   - **Dashboard:** http://localhost:8080/dashboard
   - **H2 Console:** http://localhost:8080/h2-console

## Configuration

All application settings are managed in `src/main/resources/application.properties`.

### Default Credentials
A default development user is provided for quick testing:
- **Username:** `admin`
- **Password:** `admin`

To create additional users, please use the signup page.

### File Upload Directory
You can customize the local storage path for uploaded files by modifying the `file.upload-dir` property in `application.properties`.

## Contributing

Contributions are welcome! To keep the project clean and maintainable, please follow these guidelines:

1. **Check existing issues:** Search the issue tracker to avoid duplicate submissions.
2. **Branching:** Create a descriptive branch (e.g., `feature/add-file-preview` or `fix/login-redirect`).
3. **Testing:** Ensure all new features or bug fixes include appropriate unit tests.
4. **Pull Requests:** Submit a PR for review. Please ensure your code follows the existing style (Java 21, Spring Boot 3.4.x, 4-space indentation) and includes no leftover debug logs or unused imports.

Contributors will be recognized in the `CONTRIBUTORS.md` file.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
