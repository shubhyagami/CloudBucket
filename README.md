# CloudBucket

CloudBucket is a lightweight, self‑hosted cloud‑storage solution built on Spring Boot.  
It offers secure session‑based authentication, upload/download of files up to 500 MB, a responsive dashboard, and a simple local file‑system backend, making it ideal for developers who need local storage while building Java web applications.

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
  - [Build & Run](#build--run)
  - [Docker](#docker)
  - [Access URLs](#access-urls)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Overview

CloudBucket stores files in a local directory and uses an in‑memory H2 database by default. It ships with:

- User management (signup, login, logout) backed by BCrypt password hashing.
- A responsive web dashboard for uploading, downloading, and deleting files.
- A pre‑configured development user (`admin`/`admin`) for immediate experimentation.
- An extensible design that lets you replace the storage or authentication module easily.

---

## Key Features

| Feature | Description |
|---------|-------------|
| **Secure Auth** | Session‑based login with BCrypt‑hashed passwords. |
| **File Management** | Upload, download, and delete files up to 500 MB. |
| **Dashboard** | User‑friendly UI for file operations. |
| **Database** | In‑memory H2 in `dev` profile; swap with any JDBC database. |
| **Extensibility** | Plug in custom storage or auth providers. |

---

## Getting Started

### Prerequisites

- Java 21 (or newer LTS)
- Maven (or the bundled `mvnw` wrapper)
- Docker (optional, for containerized deployment)

### Build & Run

```bash
# Clone the repository
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# Build the application (skip tests for speed)
./mvnw -DskipTests package

# Run the application
./mvnw spring-boot:run
```

The service listens on port 8080 by default. Open <http://localhost:8080/dashboard> and log in with:

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

The container exposes the same endpoints on port 8080.

### Access URLs

| Feature | URL |
|---------|-----|
| Sign‑up | <http://localhost:8080/signup> |
| Log‑in | <http://localhost:8080/login> |
| Dashboard | <http://localhost:8080/dashboard> |
| H2 Console (dev only) | <http://localhost:8080/h2-console> |

> The H2 console can be enabled in the `dev` profile with `-Dspring.profiles.active=dev`.

---

## Configuration

Edit `src/main/resources/application.properties` or override settings with environment variables:

```properties
## Development user (overridden by env vars)
app.user.username=admin
app.user.password=admin

## File storage location
file.upload-dir=${FILE_UPLOAD_DIR:/var/cloudbucket/uploads}

## Server port
server.port=8080

## H2 console (only in dev)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Property | Description | Default |
|----------|-------------|---------|
| `app.user.username` | Default login username | `admin` |
| `app.user.password` | Default login password | `admin` |
| `file.upload-dir` | Directory for uploaded files | `/var/cloudbucket/uploads` |
| `server.port` | HTTP port | `8080` |

Environment variables that map to the properties:

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

All tests use the in‑memory H2 database. Coverage reports are available in `target/site/`.

---

## Contributing

1. Fork the repository and create a descriptive branch.  
2. Run the existing tests: `./mvnw test`.  
3. Add or update tests for any new or modified functionality.  
4. Submit a pull request with a clear description of the changes.

**Code style guidelines**

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use the logging framework instead of `System.out`.

---

## License

CloudBucket is distributed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Changelog

### v1.1.0

- Added `file.upload-dir` property for configurable storage.  
- Improved error handling for oversized uploads.

### v1.0.0

- Initial release: user management, file upload/download, H2 integration, dashboard.
