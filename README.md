# CloudBucket

CloudBucket is a lightweight, self‑hosted cloud‑storage solution built with Spring Boot 3.  
It provides secure session‑based authentication, a responsive dashboard, and a simple file‑system backend, making it a quick way to add file management to any Java web application.

> **Default credentials** – `admin` / `admin`.  
> Override them with environment variables or configuration properties.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Build and Run](#build-and-run)
  - [Docker](#docker)
  - [Access URLs](#access-urls)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Overview

CloudBucket stores uploaded files in a configurable local directory and keeps metadata in an H2 database by default. The application ships with:

- **User management** – Sign‑up, login, logout using BCrypt‑hashed passwords.
- **Dashboard** – Web UI for easy file upload, download and delete.
- **Development convenience** – A pre‑configured `admin/admin` account and an `dev` profile that enables the H2 console.
- **Extensibility** – Replace the storage or authentication layers with custom implementations without changing the core logic.

---

## Key Features

| Feature | Description |
|---------|--------------|
| **Secure Auth** | Session‑based login with BCrypt hashing. |
| **File Operations** | Upload, download, and delete files up to 500 MB. |
| **Web Dashboard** | Intuitive interface for file management. |
| **Local Storage** | Files are saved under a directory you specify. |
| **Database** | In‑memory H2 for development; any JDBC database can be plugged in. |
| **Extensibility** | Swap storage or auth providers via Spring beans. |

---

## Getting Started

### Prerequisites

- **Java 21** (or newer LTS) – <https://www.oracle.com/java/>
- **Maven** (or the bundled `mvnw` wrapper)
- **Docker** (optional, for containerized deployment)

### Build and Run

```bash
# Clone the repo
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# Build the application (skip tests for speed)
./mvnw -DskipTests package

# Run the application
./mvnw spring-boot:run
```

The service starts on port **8080**. Open <http://localhost:8080/dashboard> and log in with:

- **Username**: `admin`
- **Password**: `admin`

### Docker

```bash
docker build -t cloudbucket .
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  cloudbucket
```

The container mounts the specified upload directory and exposes all endpoints on port 8080.

### Access URLs

| Feature | URL |
|---------|-----|
| Sign‑up | <http://localhost:8080/signup> |
| Log‑in | <http://localhost:8080/login> |
| Dashboard | <http://localhost:8080/dashboard> |
| H2 Console *(dev only)* | <http://localhost:8080/h2-console> |

> Enable the H2 console in the `dev` profile:  
> `./mvnw spring-boot:run -Dspring.profiles.active=dev`

---

## Configuration

Edit `src/main/resources/application.properties` or override any setting with an environment variable.

```properties
# Default user (overridden by env vars)
app.user.username=admin
app.user.password=admin

# File storage location
file.upload-dir=${FILE_UPLOAD_DIR:/var/cloudbucket/uploads}

# Server port
server.port=8080

# H2 console (only in dev)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Property | Description | Default |
|----------|-------------|---------|
| `app.user.username` | Default login username | `admin` |
| `app.user.password` | Default login password | `admin` |
| `file.upload-dir` | Directory for uploaded files | `/var/cloudbucket/uploads` |
| `server.port` | HTTP port | `8080` |

**Environment variables**

| Variable | Property |
|----------|----------|
| `APP_USER_USERNAME` | `app.user.username` |
| `APP_USER_PASSWORD` | `app.user.password` |
| `FILE_UPLOAD_DIR` | `file.upload-dir` |

---

## Running Tests

```bash
./mvnw test
```

All tests use the in‑memory H2 database. Coverage reports can be found in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run the existing tests: `./mvnw test`.  
3. Add or update tests for any new or modified functionality.  
4. Submit a pull request with a clear description of the changes.

**Style guide**

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use the SLF4J logging framework instead of `System.out`.

---

## License

CloudBucket is released under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Changelog

### v1.1.0 – 2026‑09‑04

- Added `file.upload-dir` property for configurable storage.  
- Improved error handling for oversized uploads.

### v1.0.0 – 2024‑01‑10

- Initial release: user management, file upload/download, H2 integration, dashboard.
