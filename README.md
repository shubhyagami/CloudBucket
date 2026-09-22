# CloudBucket

**CloudBucket** is a lightweight, self‑hosted cloud storage backend for Spring Boot applications. It ships with an admin dashboard, session–based authentication, and a highly configurable file storage system that can be easily integrated into your own projects or deployed as a standalone service.

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)
![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)
![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)
![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Features

- **Admin Dashboard** – Upload, download, delete and list files via a simple UI.  
- **Session‑based authentication** – Secure login with configurable credentials.  
- **Configurable storage** – Store files anywhere on the host system.  
- **Spring Boot Starter** – Add CloudBucket to your application with a single dependency.  
- **Container ready** – Docker image included for quick deployment.

---

## Quick Start

The quickest way to get CloudBucket running locally:

```
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> in your browser.  
Default credentials: `admin` / `admin`.

> **Tip** – To use the built‑in H2 console during development, start with the `dev` profile:

```
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

---

## Deployment

### Using Docker

```bash
docker build -t cloudbucket .
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  -v /your/host/path:/data/uploads \
  cloudbucket
```

> The volume mapping keeps uploaded files persistent across container restarts.

### Maven Build

```bash
./mvnw clean package -DskipTests
```

The resulting JAR can be run with:

```bash
java -jar target/cloudbucket-1.1.0.jar
```

---

## Configuration

CloudBucket can be tuned through `application.yml`, `application.properties` or environment variables. Spring Boot automatically maps `property.name` to `PROPERTY_NAME`.

| Property                           | Env Variable               | Default                         | Purpose |
|------------------------------------|----------------------------|---------------------------------|---------|
| `app.user.username`                | `APP_USER_USERNAME`         | `admin`                         | Admin login username |
| `app.user.password`                | `APP_USER_PASSWORD`        | `admin`                         | Admin login password |
| `file.upload-dir`                  | `FILE_UPLOAD_DIR`          | `/var/cloudbucket/uploads`      | Directory where files are stored |
| `server.port`                      | `SERVER_PORT`              | `8080`                          | HTTP port |
| `spring.h2.console.enabled`        | `SPRING_H2_CONSOLE_ENABLED`| `false`                         | Enable H2 console (dev only) |
| `spring.h2.console.path`           | `SPRING_H2_CONSOLE_PATH`   | `/h2-console`                   | Console path |

---

## API Overview

| Path                | Method | Note |
|---------------------|--------|------|
| `/dashboard`       | GET    | File management UI |
| `/login`            | POST   | Authenticate an user |
| `/logout`           | POST   | Invalidate the session |
| `/signup`           | POST   | Register a new user (when enabled) |
| `/h2-console`      | GET    | H2 console (profile: `dev`) |

---

## Integration as a Dependency

Add the starter to your Spring Boot app:

```xml
<dependency>
  <groupId>com.github.shubhyagami</groupId>
  <artifactId>cloudbucket-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

The starter auto‑configures the dashboard, authentication and storage. Override any bean (e.g., custom `UserDetailsService` or storage service) to change behaviour.

---

## Running Tests

```bash
./mvnw test
```

A test coverage report will be available in `target/site/`. All integration tests run against an embedded H2 database for isolation.

---

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository and create a feature branch.  
2. Run `./mvnw test` – all tests must pass.  
3. Add tests for new or changed functionality.  
4. Submit a pull request with a clear description.  

### Code Style

- Follow standard Java/Spring Boot conventions.  
- Use SLF4J for logging.  
- Document public APIs with Javadoc.  
- Keep tests deterministic and repeatable.

---

## Changelog

### 1.1.0 – 2026‑09‑04
- Added `file.upload-dir` property for custom storage paths.  
- Added 500 MB upload limit with validation and clear error responses.  

### 1.0.0 – 2024‑01‑10
- Initial release: user management, file I/O, H2 integration, and admin dashboard.

---

## License

MIT license – see the [LICENSE](LICENSE) file for details.
