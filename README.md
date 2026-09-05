# CloudBucket

A lightweight, self‑hosted cloud‑storage solution built with Spring Boot 3.  
CloudBucket provides secure session‑based authentication, a responsive web dashboard, and a simple file‑system backend, making it trivial to add file management to any Java web application.

> **Default credentials** – `admin` / `admin`  
> Override them with environment variables or configuration properties.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)](https://maven.apache.org/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)](https://hub.docker.com/r/shubhyagami/cloudbucket)
[![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Quick Start

```bash
# Clone and compile
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw -DskipTests package

# Run locally
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with the default credentials.  
The service is ready to accept file uploads right away.

---

## Features

| Feature | Description |
|---------|-------------|
| **Secure Auth** | Session‑based login, BCrypt‑hashed passwords. |
| **File Management** | Upload, download, delete files up to 500 MB. |
| **Web Dashboard** | Intuitive UI for file operations. |
| **Local Storage** | Files stored in a configurable directory. |
| **Database** | In‑memory H2 (dev) or any JDBC database via Spring Data. |
| **Extensible** | Swap storage or authentication layers with custom Spring beans. |

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Getting Started](#getting-started)
   - [Build & Run](#build--run)
   - [Docker](#docker)
   - [Access URLs](#access-urls)
3. [Configuration](#configuration)
4. [Testing](#testing)
5. [Contributing](#contributing)
6. [License](#license)
7. [Changelog](#changelog)

---

## Prerequisites

| Item | Version | Notes |
|------|----------|-------|
| Java | 21+ (LTS) | [Download](https://www.oracle.com/java/) |
| Maven | 3.9.6+ | Or use the bundled `mvnw` wrapper |
| Docker | 19.03+ | Optional, for containerized deployment |

---

## Getting Started

### Build & Run

```bash
# Clone the repo
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# Build (skip tests for speed)
./mvnw -DskipTests package

# Run the application
./mvnw spring-boot:run
```

The service listens on **port 8080**. Open <http://localhost:8080/dashboard> and log in as:

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

The container mounts the directory specified by `FILE_UPLOAD_DIR` and exposes all endpoints on port 8080.

### Access URLs

| Feature | URL |
|---------|-----|
| Sign‑up | <http://localhost:8080/signup> |
| Login | <http://localhost:8080/login> |
| Dashboard | <http://localhost:8080/dashboard> |
| H2 Console (dev only) | <http://localhost:8080/h2-console> |

> To enable the H2 console, run with the `dev` profile:  
> `./mvnw spring-boot:run -Dspring.profiles.active=dev`

---

## Configuration

Edit `src/main/resources/application.properties` or override any setting with an environment variable.

```properties
# Default user (overridden by env vars)
app.user.username=admin
app.user.password=admin

# Directory where uploaded files are stored
file.upload-dir=${FILE_UPLOAD_DIR:/var/cloudbucket/uploads}

# Server HTTP port
server.port=8080

# H2 console (only enabled in dev)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Property | Description | Default |
|----------|-------------|---------|
| `app.user.username` | Default login username | `admin` |
| `app.user.password` | Default login password | `admin` |
| `file.upload-dir` | Path to local storage | `/var/cloudbucket/uploads` |
| `server.port` | HTTP port | `8080` |

**Environment variables**

| Variable | Property |
|----------|----------|
| `APP_USER_USERNAME` | `app.user.username` |
| `APP_USER_PASSWORD` | `app.user.password` |
| `FILE_UPLOAD_DIR` | `file.upload-dir` |

---

## Testing

```bash
./mvnw test
```

All tests use an in‑memory H2 database. Test coverage reports are generated in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run the existing tests: `./mvnw test`.  
3. Add or update tests for any new or modified functionality.  
4. Submit a pull request with a clear description of the changes.

**Style guide**

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use SLF4J for logging instead of `System.out`.

---

## License

CloudBucket is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Changelog

### v1.1.0 – 2026‑09‑04

- Added `file.upload-dir` property for configurable storage.  
- Improved error handling for oversized uploads.

### v1.0.0 – 2024‑01‑10

- Initial release: user management, file upload/download, H2 integration, dashboard.
