# CloudBucket

**CloudBucket** is a lightweight, self-hosted cloud storage backend for Spring Boot applications. It includes an admin dashboard, session-based authentication, and a configurable file storage system. Use it as a standalone service or integrate it into an existing Spring Boot project.

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)
![Release](https://img.shields.io/github/v/release/shubhyagami/CloudBucket?style=flat-square)
![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)
![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)
![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Features

- **Admin Dashboard** – Upload, download, delete, and list files through a simple UI.
- **Session-based Authentication** – Secure login with configurable credentials.
- **Configurable Storage** – Store files anywhere on the host system.
- **Spring Boot Starter** – Add CloudBucket to your application with a single dependency.
- **Container Ready** – Docker image included for quick deployment.
- **Development-friendly** – Optional H2 console for local development.

---

## Quick Start

Requirements: Java 21. The Maven wrapper handles Maven automatically.

```bash
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> in your browser.

Default credentials: `admin` / `admin`.

> Change the default credentials before exposing the service.

To enable the H2 console during development, start the application with the `dev` profile:

```bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

The H2 console is then available at <http://localhost:8080/h2-console>.

---

## Configuration

CloudBucket can be configured through `application.yml`, `application.properties`, or environment variables. Spring Boot automatically maps `property.name` to `PROPERTY_NAME`.

| Property                    | Env Variable                | Default                    | Purpose |
|-----------------------------|-----------------------------|----------------------------|---------|
| `app.user.username`         | `APP_USER_USERNAME`         | `admin`                    | Admin login username |
| `app.user.password`         | `APP_USER_PASSWORD`         | `admin`                    | Admin login password |
| `file.upload-dir`           | `FILE_UPLOAD_DIR`           | `/var/cloudbucket/uploads` | Directory where files are stored |
| `server.port`               | `SERVER_PORT`               | `8080`                     | HTTP port |
| `spring.h2.console.enabled` | `SPRING_H2_CONSOLE_ENABLED` | `false`                    | Enable H2 console for development |
| `spring.h2.console.path`    | `SPRING_H2_CONSOLE_PATH`    | `/h2-console`              | H2 console path |

---

## Deployment

### Docker

Build the image:

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

The volume mapping keeps uploaded files persistent across container restarts.

If you prefer the published image, replace `cloudbucket` with `shubhyagami/cloudbucket`.

### Build from Source

```bash
./mvnw clean package -DskipTests
```

Run the resulting JAR:

```bash
java -jar target/cloudbucket-*.jar
```

---

## API Overview

| Method | Path           | Notes |
|--------|----------------|-------|
| GET    | `/dashboard`   | File management UI |
| POST   | `/login`       | Authenticate a user |
| POST   | `/logout`      | Invalidate the current session |
| POST   | `/signup`      | Register a new user, when enabled |
| GET    | `/h2-console`  | H2 console, available with the `dev` profile |

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

The starter auto-configures the dashboard, authentication, and storage. You can override any bean, such as a custom `UserDetailsService` or storage service, to change the default behaviour.

---

## Development

### Running Tests

```bash
./mvnw test
```

A test coverage report will be available in `target/site/`. Integration tests run against an embedded H2 database for isolation.

### Code Style

- Follow standard Java and Spring Boot conventions.
- Use SLF4J for logging.
- Document public APIs with Javadoc.
- Keep tests deterministic and repeatable.

---

## Contributing

Contributions are welcome. Please follow these steps:

1. Fork the repository and create a feature branch.
2. Run `./mvnw test` – all tests must pass.
3. Add tests for new or changed functionality.
4. Submit a pull request with a clear description.

---

## Changelog

### 1.1.0 – 2026-09-04

- Added the `file.upload-dir` property for custom storage paths.
- Added a 500 MB upload limit with validation and clear error responses.

### 1.0.0 – 2024-01-10

- Initial release: user management, file I/O, H2 integration, and admin dashboard.

---

## License

MIT License – see the [LICENSE](LICENSE) file for details.
