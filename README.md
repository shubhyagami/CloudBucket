# CloudBucket

A lightweight, self‑hosted cloud storage service built with Spring Boot.  
It provides secure authentication, file upload/download (up to 500 MB per file), and a minimalist dashboard – a quick sandbox for developers experimenting with Java web applications and local storage.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Table of Contents

1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Getting Started](#getting-started)
   - [Build & Run](#build--run)
   - [Access URLs](#access-urls)
4. [Configuration](#configuration)
5. [Running Tests](#running-tests)
6. [Contributing](#contributing)
7. [License](#license)
8. [Changelog](#changelog)

---

## Overview

CloudBucket is a Spring Boot application that stores files in a configurable local directory and uses an in‑memory H2 database for development. It includes:

- Basic user management (signup, login, logout).
- A web dashboard for file upload, download, and listing.
- Secure password hashing with BCrypt.
- A pre‑configured development user (`admin`/`admin`) for quick testing.

The repository is intentionally minimal, making it easy to understand and extend.

---

## Key Features

- **Secure authentication** using BCrypt password hashing.
- **File management**:
  - Upload, download, and delete files up to 500 MB.
  - Configurable storage location via `file.upload-dir`.
- **Dashboard**: Simple, responsive UI for file operations.
- **Database**: In‑memory H2 for rapid setup; can be swapped with a persistent database.
- **Base user**: `admin` with password `admin` for development out of the box.
- **Extensibility**: Plug‑in your own storage and authentication mechanisms.

---

## Getting Started

### Build & Run

```bash
# Build the application (skip tests for a quick build)
./mvnw -DskipTests package

# Run the application
./mvnw spring-boot:run
```

The default port is **8080**. Adjust if needed with the `server.port` property.

### Access URLs

| Feature          | URL                                   |
|------------------|---------------------------------------|
| Signup           | `http://localhost:8080/signup`        |
| Login            | `http://localhost:8080/login`         |
| Dashboard        | `http://localhost:8080/dashboard`      |
| H2 Console       | `http://localhost:8080/h2-console`     |

> **Tip**: The H2 console is enabled only in the `dev` profile. Use `-Dspring.profiles.active=dev` to activate it.

---

## Configuration

Edit `src/main/resources/application.properties` (or provide environment variables) to fine‑tune behavior.

```properties
# Development user (overridden by env vars)
app.user.username=admin
app.user.password=admin

# File storage location
file.upload-dir=/var/cloudbucket/uploads

# Spring Boot settings
server.port=8080
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Environment‑variable overrides** (e.g., `APP_USER_USERNAME`, `FILE_UPLOAD_DIR`).  
Use `docker run` or a CI/CD pipeline to provide these.

---

## Running Tests

All functional and unit tests use the H2 in‑memory database.

```bash
# Run the full test suite
./mvnw test
```

The test coverage is logged in the `target/site/` folder.

---

## Contributing

1. **Fork** the repository and create a descriptive branch.
2. **Read** the Javadoc and comments in the codebase.
3. **Run** the test suite locally (`./mvnw test`) to confirm baseline functionality.
4. **Add** tests for any new or changed behavior.
5. **Push** and submit a pull request with a concise description.

### Code Style

- Follow the established Java and Spring conventions.
- Document public APIs with Javadoc.
- Avoid printing debug information to the console.

---

## License

CloudBucket is released under the [MIT License](LICENSE).

---

## Changelog

### v1.1.0
- Added configurable upload directory (`file.upload-dir`).
- Improved error handling for invalid file uploads.

### v1.0.0
- Initial release: user management, file upload/download, H2 integration, dashboard.

---

> Maintained with ❤️ by the CloudBucket maintainer.
