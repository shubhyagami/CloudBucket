# CloudBucket

A lightweight, self‑hosted cloud‑storage backend for Spring Boot applications.  
It offers secure session‑based authentication, a responsive dashboard, and local file‑system storage.

> **Default credentials** – `admin` / `admin`  
> Override them with environment variables or configuration properties.

[![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)  
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)  
[![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)  
[![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)](https://maven.apache.org/)  
[![Docker pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)](https://hub.docker.com/r/shubhyagami/cloudbucket)  
[![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Overview

**CloudBucket** is a drop‑in Spring Boot 3.4.1 starter that provides:

- **Session‑based authentication** with BCrypt‑hashed passwords.
- A responsive file‑management UI under `/dashboard`.
- Lightweight local file‑system storage (configurable path).
- Optional user registration (`/signup` endpoint).
- A flexible persistence layer – H2 for quick starts, or any JDBC database via Spring Data JPA.
- Easy extension: replace authentication or storage components with custom Spring beans.

---

## Features

| Feature                    | Description                                                  |
|--------------------------- |--------------------------------------------------------------|
| Secure auth                | Session login, BCrypt passwords, optional signup             |
| File operations            | Upload, download, delete (max 500 MB)                        |
| Dashboard                  | Responsive UI under `/dashboard`                              |
| Configurable storage       | Default `file.upload-dir=/var/cloudbucket/uploads`          |
| Persistence                | H2 (dev), any JDBC DB via Spring Data JPA                   |
| Extensibility              | Swap auth or storage via Spring beans                         |

---

## Prerequisites

- **Java 21** (or newer)
- **Maven 3.9.6** (or the bundled wrapper `./mvnw`)
- **Docker** (optional, for containerised deployment)

---

## Getting Started

```bash
# 1. Clone
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build (skip tests for a fast dev run)
./mvnw -DskipTests package

# 3. Run
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with **admin/admin**.  
The service is now ready to accept file uploads.

> **Tip** – Enable the H2 console for development:
> ```bash
> ./mvnw spring-boot:run -Dspring.profiles.active=dev
> ```

---

## Docker

```bash
docker build -t cloudbucket .
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  cloudbucket
```

Mount a host directory to `/data/uploads` to persist uploads between restarts.

---

## Configuration

CloudBucket reads properties from `application.properties` (or `application.yml`); environment variables can override them.  
Env vars follow the pattern `PREFIX_PROPERTY_NAME` (upper‑case, periods → underscores).

| Property                     | Description                          | Default                     |
|------------------------------|--------------------------------------|-----------------------------|
| `app.user.username`          | Default login username              | `admin`                     |
| `app.user.password`          | Default login password              | `admin`                     |
| `file.upload-dir`            | Path for uploaded files             | `/var/cloudbucket/uploads` |
| `server.port`                | HTTP port                            | `8080`                      |
| `spring.h2.console.enabled`   | Enable H2 console (dev profile only)| `false`                    |
| `spring.h2.console.path`    | H2 console path (dev profile only)   | `/h2-console`               |

**Environment variable mapping**

| Environment Variable | Maps to Property          |
|----------------------|--------------------------|
| `APP_USER_USERNAME`  | `app.user.username`      |
| `APP_USER_PASSWORD`  | `app.user.password`      |
| `FILE_UPLOAD_DIR`     | `file.upload-dir`        |
| `SERVER_PORT`         | `server.port`            |

---

## Endpoints

| Path          | Purpose                         |
|---------------|---------------------------------|
| `/dashboard`  | File‑management UI             |
| `/signup`     | Register a new user (optional)  |
| `/login`      | User login                      |
| `/logout`     | User logout                     |
| `/h2-console` | H2 console (dev profile only)  |

---

## Testing

```bash
./mvnw test
```

All tests run against an in‑memory H2 database.  
Coverage reports are available in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run `./mvnw test` to confirm the build passes.  
3. Add or update tests for any new behaviour.  
4. Submit a pull request with a clear description of your changes.

### Coding style

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use SLF4J for logging (avoid `System.out`).  
- Keep tests isolated and deterministic.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

### v1.1.0 – 2026‑09‑04

* Added `file.upload-dir` property for configurable storage.  
* Improved error handling for oversized uploads.

### v1.0.0 – 2024‑01‑10

* Initial release: user management, file upload/download, H2 integration, dashboard.
