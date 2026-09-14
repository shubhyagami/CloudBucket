# CloudBucket

**CloudBucket** is a lightweight, self‑hosted cloud‑storage backend for Spring Boot 3.4.1 applications.  
It supplies session‑based authentication, a responsive file‑management UI, and local filesystem storage out of the box.

> **Default credentials** – `admin` / `admin`  
> Override them with environment variables or Spring properties.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)  
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)  
[![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)  
[![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)](https://maven.apache.org/)  
[![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)](https://hub.docker.com/r/shubhyagami/cloudbucket)  
[![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Overview

CloudBucket is a Spring Boot starter that provides:

* **Session‑based login** with BCrypt‑hashed passwords.
* A **responsive dashboard** at `/dashboard` for file upload, download, and deletion (max 500 MB).
* **Local filesystem storage** (configurable path).
* Optional **user registration** at `/signup`.
* **Persistence**: H2 for quick starts or any JDBC database through Spring Data JPA.
* **Extensibility**: Replace authentication or storage with custom Spring beans.

---

## Features

| Feature | Details |
|---------|---------|
| Authentication | Session login, BCrypt passwords, optional signup |
| File operations | Upload, download, delete (≤ 500 MB) |
| Dashboard | `/dashboard` – responsive UI |
| Storage | Default: `/var/cloudbucket/uploads`; configurable via `file.upload-dir` |
| Persistence | H2 (dev) or any JDBC database |
| Extensibility | Replace auth or storage beans |

---

## Prerequisites

| Tool | Minimum version |
|------|-----------------|
| Java | 21 |
| Maven | 3.9.6 (or the Maven Wrapper `./mvnw`) |
| Docker | optional – for containerised deployment |

---

## Quick Start

```bash
# 1. Clone
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build (skip tests for a fast dev run)
./mvnw -DskipTests package

# 3. Run
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with `admin/admin`.  
The service is ready for file uploads.

> **Development mode** – enable H2 console:  
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

Mount a host directory to `/data/uploads` to persist files between restarts.

---

## Configuration

CloudBucket reads `application.properties` (or `application.yml`).  
Environment variables override properties and follow the pattern `PREFIX_PROPERTY_NAME` (upper‑case, periods → underscores).

| Property                     | Default                     | Description                     |
|------------------------------|-----------------------------|---------------------------------|
| `app.user.username`          | `admin`                     | Default login username          |
| `app.user.password`          | `admin`                     | Default login password          |
| `file.upload-dir`            | `/var/cloudbucket/uploads` | Path for uploaded files         |
| `server.port`                | `8080`                      | HTTP port                       |
| `spring.h2.console.enabled` | `false`                     | Enable H2 console (dev profile)|
| `spring.h2.console.path`     | `/h2-console`               | H2 console path (dev profile)  |

**Environment variables**

| Variable | Maps to property          |
|---------|---------------------------|
| `APP_USER_USERNAME` | `app.user.username` |
| `APP_USER_PASSWORD` | `app.user.password` |
| `FILE_UPLOAD_DIR` | `file.upload-dir` |
| `SERVER_PORT` | `server.port` |

---

## Endpoints

| Path            | Purpose                |
|-----------------|------------------------|
| `/dashboard`    | File‑management UI    |
| `/signup`       | Register a new user    |
| `/login`        | User login             |
| `/logout`       | User logout             |
| `/h2-console`  | H2 console (dev only) |

---

## Testing

```bash
./mvnw test
```

All tests run against an in‑memory H2 database.  
Coverage reports are available in `target/site/`.

---

## Contributing

1. Fork the repo and create a feature branch.  
2. Run `./mvnw test` to ensure the build passes.  
3. Add or update tests for any new behaviour.  
4. Submit a pull request with a clear description.

### Coding style

* Follow Java and Spring conventions.  
* Document public APIs with Javadoc.  
* Use SLF4J for logging.  
* Keep tests isolated and deterministic.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

### v1.1.0 – 2026‑09‑04

* Introduced `file.upload-dir` property for configurable storage location.  
* Added error handling for uploads exceeding 500 MB.

### v1.0.0 – 2024‑01‑10

* Initial release: user management, file upload/download, H2 integration, dashboard.
