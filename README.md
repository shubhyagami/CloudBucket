# CloudBucket

A lightweight, self‑hosted cloud‑storage solution built on Spring Boot 3.  
It adds secure, session‑based authentication, a responsive dashboard and simple file‑system storage to any Java web application.

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

CloudBucket provides a simple, secure file‑sharing backend that can be dropped into any Spring Boot application.  
Key concepts:

* **Session‑based authentication** with BCrypt‑hashed passwords.
* **Web dashboard** for uploading, downloading, and deleting files.
* **Local file‑system storage** that can be configured via a single property.
* **Flexible data layer** – H2 for quick starts, any JDBC database for production.
* **Extensible architecture** – swap out authentication or storage components with Spring beans.

---

## Features

| Feature | Description |
|---------|-------------|
| Secure auth | Session login, BCrypt passwords, optional signup. |
| File ops | Upload, download, delete (max 500 MB). |
| Dashboard | Responsive UI under `/dashboard`. |
| Configurable storage | Default path `/var/cloudbucket/uploads`; override with `FILE_UPLOAD_DIR`. |
| Persistence | H2 (dev) or any JDBC DB via Spring Data JPA. |
| Extensibility | Replace auth or storage with custom Spring beans. |

---

## Prerequisites

* **Java 21** or newer
* **Maven 3.9.6** (or use the embedded Maven wrapper)
* **Docker** (optional, for containerised deployment)

---

## Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build (skip tests for a quick dev build)
./mvnw -DskipTests package

# 3. Run the application
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with the default credentials (`admin`/`admin`).  
The service is now ready to accept file uploads.

> **Tip:** To enable the H2 console, run with the `dev` profile:  
> `./mvnw spring-boot:run -Dspring.profiles.active=dev`

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

Mount a host directory to `/data/uploads` to persist uploads across container restarts.

---

## Configuration

CloudBucket reads properties from `application.properties` (or `application.yml`) and can be overridden by environment variables.  
Environment variables use the same key names with periods replaced by underscores and converted to upper‑case.

### application.properties example

```properties
# Default login (override with env vars)
app.user.username=admin
app.user.password=admin

# Upload directory (env: FILE_UPLOAD_DIR)
file.upload-dir=${FILE_UPLOAD_DIR:/var/cloudbucket/uploads}

# HTTP port
server.port=8080

# H2 console (enabled only for the dev profile)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Properties table

| Property               | Description                               | Default                          |
|------------------------|-------------------------------------------|----------------------------------|
| `app.user.username`   | Default login username                    | `admin`                          |
| `app.user.password`   | Default login password                    | `admin`                          |
| `file.upload-dir`     | Path for uploaded files                    | `/var/cloudbucket/uploads`      |
| `server.port`         | HTTP port                                 | `8080`                           |

### Environment variables mapping

| Variable              | Property           |
|-----------------------|-------------------|
| `APP_USER_USERNAME`   | `app.user.username` |
| `APP_USER_PASSWORD`   | `app.user.password` |
| `FILE_UPLOAD_DIR`     | `file.upload-dir`   |

---

## Endpoints

| Path          | Purpose                      |
|---------------|------------------------------|
| `/dashboard`  | File‑management UI          |
| `/signup`     | Register a new user (optional)|
| `/login`      | User login                   |
| `/logout`     | User logout                  |
| `/h2-console` | H2 console (dev profile only)|

---

## Testing

```bash
./mvnw test
```

All tests run against an in‑memory H2 database.  
Coverage reports are generated in `target/site/`.

---

## Contributing

1. Fork the repo and create a feature branch.  
2. Ensure the build passes (`./mvnw test`).  
3. Add or update tests for any new behaviour.  
4. Submit a PR with a clear description of your changes.

### Coding style

* Follow standard Java and Spring conventions.  
* Document public APIs with Javadoc.  
* Use SLF4J for logging (avoid `System.out`).  
* Keep tests isolated and deterministic.

---

## License

CloudBucket is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Changelog

### v1.1.0 – 2026‑09‑04
* Added `file.upload-dir` property for configurable storage.
* Improved error handling for oversized uploads.

### v1.0.0 – 2024‑01‑10
* Initial release: user management, file upload/download, H2 integration, dashboard.
