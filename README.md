[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# CloudBucket

**CloudBucket** is a lightweight, self‑hosted cloud storage backend for Spring Boot applications.  
It provides an admin dashboard, session‑based authentication, and a configurable storage area that can be used as a standalone service or integrated into an existing Spring Boot project.

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)  
![Release](https://img.shields.io/github/v/release/shubhyagami/CloudBucket?style=flat-square)  
![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)  
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)  
![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)  
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)  
![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Features

- **Admin Dashboard** – Upload, download, delete, and list files with a simple UI.  
- **Session‑based Auth** – Secure against unauthorized access with configurable credentials.  
- **Configurable Storage** – Store files anywhere on the host system; the default path is `/var/cloudbucket/uploads`.  
- **Spring Boot Starter** – Add CloudBucket to your project with a single dependency.  
- **Docker‑ready** – Official Docker image available on Docker Hub.  
- **Developer Friendly** – Optional embedded H2 console for local testing.

---

## Quick Start

### 1. Run locally (Maven wrapper)

```
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> in a browser.  
Default credentials: `admin` / `admin`.  
> **Important** – change these before exposing the service.

### 2. Enable H2 console during development

```
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

The console is available at <http://localhost:8080/h2-console>.

---

## Configuration

CloudBucket uses Spring Boot's relaxed binding.  
Properties can be set in `application.yml`, `application.properties`, or as environment variables.  
The table below shows the most common settings.

| Property                    | Environment Variable                | Default                        | Description |
|-----------------------------|--------------------------------------|--------------------------------|-------------|
| `app.user.username`         | `APP_USER_USERNAME`                  | `admin`                        | Admin login username |
| `app.user.password`         | `APP_USER_PASSWORD`                  | `admin`                        | Admin login password |
| `file.upload-dir`           | `FILE_UPLOAD_DIR`                    | `/var/cloudbucket/uploads`    | Directory for uploaded files |
| `server.port`               | `SERVER_PORT`                        | `8080`                         | HTTP listening port |
| `spring.h2.console.enabled`| `SPRING_H2_CONSOLE_ENABLED`          | `false`                        | Enable H2 console (dev only) |
| `spring.h2.console.path`   | `SPRING_H2_CONSOLE_PATH`            | `/h2-console`                  | H2 console path |

---

## Deployment

### Docker

Build a local image (optional – the image is published on Docker Hub):

```
docker build -t cloudbucket .
```

Run the container:

```
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  -v /your/host/path:/data/uploads \
  cloudbucket
```

The volume mount keeps uploaded files persistent.  
To use the public image, replace `cloudbucket` with `shubhyagami/cloudbucket` in the `docker run` command.

### Build from Source

```
./mvnw clean package -DskipTests
java -jar target/cloudbucket-*.jar
```

---

## API Overview

| Method | Path           | Description |
|--------|----------------|-------------|
| GET    | `/dashboard`  | File‑management UI |
| POST   | `/login`       | Authenticate a user |
| POST   | `/logout`      | Invalidate the current session |
| POST   | `/signup`      | Register a new user (enabled via configuration) |
| GET    | `/h2-console`  | H2 console (requires `dev` profile) |

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

The starter auto‑configures the dashboard, authentication, and storage.  
Override any bean (e.g. `UserDetailsService` or storage implementation) to customize behaviour.

---

## Development

### Running Tests

```
./mvnw test
```

A coverage report is generated in `target/site/`.  
Integration tests use an embedded H2 database to avoid side effects.

### Code Style

- Follow standard Java and Spring Boot conventions.  
- Use SLF4J for logging.  
- Document public APIs with Javadoc.  
- Keep tests deterministic and repeatable.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run `./mvnw test` – all tests must pass.  
3. Add tests for new or changed functionality.  
4. Submit a pull request with a clear description.

---

## Changelog

### 1.1.0 – 2026‑09‑04
- Added customizable `file.upload-dir` property.  
- Introduced a 500 MB upload limit with validation and clear error responses.

### 1.0.0 – 2024‑01‑10
- Initial release: user management, file I/O, H2 integration, and admin dashboard.

---

## License

MIT License – see the [LICENSE](LICENSE) file for details.
