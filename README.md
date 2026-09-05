# CloudBucket

A lightweight, self‑hosted cloud‑storage solution built on Spring Boot 3.  
CloudBucket adds secure, session‑based authentication, a responsive dashboard
and simple file‑system storage to any Java web application.

> **Default credentials** – `admin` / `admin`  
> Override them with environment variables or configuration properties.

[![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)](https://maven.apache.org/)
[![Docker pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)](https://hub.docker.com/r/shubhyagami/cloudbucket)
[![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build (skip tests for faster dev build)
./mvnw -DskipTests package

# 3. Run the application
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with the default credentials.  
The service is now ready to accept file uploads.

---

## Features

- **Secure authentication** – Session‑based login, BCrypt‑hashed passwords.  
- **File management** – Upload, download, delete files (up to 500 MB).  
- **Web dashboard** – Intuitive UI for all file operations.  
- **Configurable storage** – Store files in a local directory (default
  `/var/cloudbucket/uploads`).  
- **Flexible persistence** – In‑memory H2 for development or any JDBC
  database with Spring Data.  
- **Extensible architecture** – Replace authentication or storage layers
  with custom Spring beans.

---

## Configuration

Edit `src/main/resources/application.properties` or supply environment
variables.  
Environment variables follow the same naming convention as the property key
(with periods replaced by underscores and upper‑cased).

```properties
# Default user (override with env vars)
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

| Property              | Description                          | Default                   |
|-----------------------|--------------------------------------|---------------------------|
| `app.user.username`  | Default login username                | `admin`                  |
| `app.user.password`  | Default login password                | `admin`                  |
| `file.upload-dir`     | Path to local storage                | `/var/cloudbucket/uploads`|
| `server.port`         | HTTP port                            | `8080`                    |

**Environment variables**

| Variable           | Property             |
|--------------------|----------------------|
| `APP_USER_USERNAME` | `app.user.username`   |
| `APP_USER_PASSWORD` | `app.user.password`   |
| `FILE_UPLOAD_DIR`   | `file.upload-dir`     |

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

Mount the directory you want to persist uploads to, and expose port 8080.

---

## Access URLs

| Endpoint            | Purpose                              |
|---------------------|--------------------------------------|
| `/dashboard`        | Main file‑management UI             |
| `/signup`           | Create a new user (optional)         |
| `/login`            | Log in                                 |
| `/h2-console`       | H2 console (dev profile only)        |

> **H2 console**  
> Enabled only with the `dev` profile:  
> `./mvnw spring-boot:run -Dspring.profiles.active=dev`

---

## Testing

```bash
./mvnw test
```

All tests run against an in‑memory H2 database. Coverage reports are
generated in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run the existing tests to confirm the build is green.  
3. Add or update tests for any new or changed functionality.  
4. Submit a pull request with a clear description of your changes.

**Coding style**

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use SLF4J for logging instead of `System.out`.

---

## License

CloudBucket is licensed under the MIT License. See the
[LICENSE](LICENSE) file for details.

---

## Changelog

### v1.1.0 – 2026‑09‑04

- Added `file.upload-dir` property for configurable storage.  
- Improved error handling for oversized uploads.

### v1.0.0 – 2024‑01‑10

- Initial release: user management, file upload/download, H2 integration, dashboard.
