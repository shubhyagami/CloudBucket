# CloudBucket

CloudBucket is a lightweight, self-hosted cloud storage backend for Spring Boot applications. It ships with an admin dashboard, session-based authentication, and a configurable storage area, and it can run either as a standalone service or be embedded into an existing Spring Boot project via the provided starter.

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&style=flat-square&label=build)
![Release](https://img.shields.io/github/v/release/shubhyagami/CloudBucket?style=flat-square&label=release)
![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
  - [Run Locally with Maven](#run-locally-with-maven)
  - [Run in Development Mode](#run-in-development-mode)
- [Configuration](#configuration)
- [Deployment](#deployment)
  - [Docker](#docker)
  - [Build from Source](#build-from-source)
- [API Overview](#api-overview)
- [Using CloudBucket as a Dependency](#using-cloudbucket-as-a-dependency)
- [Development](#development)
  - [Running Tests](#running-tests)
  - [Code Style](#code-style)
- [Contributing](#contributing)
- [Changelog](#changelog)
- [License](#license)

---

## Features

- **Admin Dashboard** – Upload, download, list, and delete files through a web UI.
- **Session-Based Authentication** – Protect access with configurable credentials.
- **Configurable Storage** – Store files anywhere on the host; the default path is `/var/cloudbucket/uploads`.
- **Upload Limits** – 500 MB per-file limit with clear error handling.
- **Spring Boot Starter** – Integrate CloudBucket into your own app with a single Maven/Gradle dependency.
- **Docker-Ready** – Official image published on Docker Hub.
- **Embedded H2 Console** – Handy for local development and debugging.

---

## Requirements

- Java 21 or later
- Maven 3.9+ (or use the included Maven wrapper, `./mvnw`)
- Docker (optional, for containerized deployment)

---

## Getting Started

### Run Locally with Maven

```bash
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> in your browser. The default credentials are `admin` / `admin`.

> **Important:** Change the default credentials via `APP_USER_USERNAME` and `APP_USER_PASSWORD` before exposing the service to any network.

### Run in Development Mode

```bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

The H2 console is then available at <http://localhost:8080/h2-console>.

---

## Configuration

CloudBucket uses Spring Boot's relaxed binding, so properties can be set in `application.yml`, `application.properties`, or as environment variables.

| Property                    | Environment Variable         | Default                    | Description                           |
|-----------------------------|------------------------------|----------------------------|---------------------------------------|
| `app.user.username`         | `APP_USER_USERNAME`          | `admin`                    | Admin login username                  |
| `app.user.password`         | `APP_USER_PASSWORD`          | `admin`                    | Admin login password                  |
| `file.upload-dir`           | `FILE_UPLOAD_DIR`            | `/var/cloudbucket/uploads` | Directory for uploaded files          |
| `server.port`               | `SERVER_PORT`                | `8080`                     | HTTP listening port                   |
| `spring.h2.console.enabled` | `SPRING_H2_CONSOLE_ENABLED`  | `false`                    | Enable the H2 console (dev only)      |
| `spring.h2.console.path`    | `SPRING_H2_CONSOLE_PATH`     | `/h2-console`              | Path of the H2 console                |

---

## Deployment

### Docker

Build the image locally (optional — an image is also published on Docker Hub):

```bash
docker build -t cloudbucket .
```

Run the container:

```bash
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  -v /your/host/path:/data/uploads \
  cloudbucket
```

The volume mount keeps uploaded files persistent across container restarts. To use the published image instead of a locally built one, replace `cloudbucket` with `shubhyagami/cloudbucket`.

### Build from Source

```bash
./mvnw clean package -DskipTests
java -jar target/cloudbucket-*.jar
```

---

## API Overview

| Method | Path          | Description                                      |
|--------|---------------|--------------------------------------------------|
| GET    | `/dashboard`  | File-management UI (requires authentication)     |
| POST   | `/login`      | Authenticate a user                              |
| POST   | `/logout`     | Invalidate the current session                   |
| POST   | `/signup`     | Register a new user (enabled via configuration)  |
| GET    | `/h2-console` | H2 database console (`dev` profile only)         |

---

## Using CloudBucket as a Dependency

Add the starter to your Spring Boot application:

```xml
<dependency>
  <groupId>com.github.shubhyagami</groupId>
  <artifactId>cloudbucket-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

The starter auto-configures the dashboard, authentication, and storage. To customize behavior, override any relevant bean — for example, `UserDetailsService` or a custom storage implementation.

---

## Development

### Running Tests

```bash
./mvnw test
```

A coverage report is generated under `target/site/`. Integration tests use an embedded H2 database to avoid side effects on real data.

### Code Style

- Follow standard Java and Spring Boot conventions.
- Use SLF4J for logging.
- Document public APIs with Javadoc.
- Keep tests deterministic and repeatable.

---

## Contributing

1. Fork the repository and create a feature branch.
2. Run `./mvnw test` — all tests must pass.
3. Add tests for any new or changed functionality.
4. Submit a pull request with a clear description of the change.

---

## Changelog

### 1.1.0 – 2026-09-04
- Added the `file.upload-dir` property for a custom storage location.
- Introduced a 500 MB per-file upload limit with clear error handling.

### 1.0.0 – 2024-01-10
- Initial release: user management, file I/O, H2 integration, and the admin dashboard.

---

## License

MIT — see the [LICENSE](LICENSE) file.
