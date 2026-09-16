# CloudBucket

**CloudBucket** is a lightweight, self‑hosted cloud‑storage backend for Spring Boot applications.  
It bundles:

- Session‑based authentication (BCrypt passwords)
- A responsive file‑management dashboard (`/dashboard`)
- Local filesystem storage (configurable path)
- Optional user signup (`/signup`)
- Persistence with H2 (dev) or any JDBC database via Spring Data JPA
- A straightforward Spring Boot starter that can be swapped out or extended

> **Default credentials:** `admin / admin`  
> Override them with environment variables or properties.

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)  
![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square)  
![Spring Boot 3.4.1](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)  
![Maven 3.9.6](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)  
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)  
![MIT License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Build & Run](#build--run)
  - [Docker](#docker)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)
- [Changelog](#changelog)
- [License](#license)

---

## Getting Started

### Prerequisites

| Tool      | Minimum version |
|-----------|-----------------|
| Java      | 21              |
| Maven     | 3.9.6 (or `./mvnw`) |
| Docker    | optional, for containerized deployment |

### Build & Run

```bash
# 1. Clone the repository
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build (skip tests for a quicker dev cycle)
./mvnw -DskipTests package

# 3. Run
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with `admin/admin`.  
The default limit for file uploads is 500 MB.

**Development mode** (enable H2 console):

```bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

### Docker

```bash
docker build -t cloudbucket .

docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  -v /host/path:/data/uploads \
  cloudbucket
```

The `-v` flag persists uploaded files across restarts.

---

## Configuration

CloudBucket reads `application.properties` (or `application.yml`).  
Environment variables can override any property. Property names are written in lower‑case dotted style; the corresponding variable is upper‑case with dots replaced by underscores.

| Property                     | Default                       | Description                                                  |
|------------------------------|-------------------------------|--------------------------------------------------------------|
| `app.user.username`         | `admin`                       | Default login username                                       |
| `app.user.password`          | `admin`                       | Default login password                                       |
| `file.upload-dir`            | `/var/cloudbucket/uploads`   | Directory where uploaded files are stored                  |
| `server.port`                | `8080`                        | HTTP port                                                   |
| `spring.h2.console.enabled`  | `false`                       | Enable H2 console (dev profile)                              |
| `spring.h2.console.path`     | `/h2-console`                 | H2 console base path                                         |

Example environment variable mapping:

| Variable          | Property            |
|------------------|---------------------|
| `APP_USER_USERNAME` | `app.user.username` |
| `APP_USER_PASSWORD` | `app.user.password` |
| `FILE_UPLOAD_DIR`   | `file.upload-dir`  |
| `SERVER_PORT`       | `server.port`       |

---

## Endpoints

| Path           | Purpose                                 |
|----------------|-----------------------------------------|
| `/dashboard`   | File‑management UI (upload / download) |
| `/signup`      | Register a new user (optional)          |
| `/login`       | User login                              |
| `/logout`      | User logout                              |
| `/h2-console`  | H2 console (enabled only in `dev`)       |

---

## Development

CloudBucket is a Spring Boot starter. To use it in another project, add the following dependency:

```xml
<dependency>
  <groupId>com.github.shubhyagami</groupId>
  <artifactId>cloudbucket-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

You can replace the default authentication or storage beans by declaring your own beans with matching types.

---

## Testing

Run the full test suite against an in‑memory H2 database:

```bash
./mvnw test
```

Coverage reports are available in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run `./mvnw test` to ensure the build passes.  
3. Add or update tests for any new behaviour.  
4. Submit a pull request with a clear description.

### Coding style

- Follow Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use SLF4J for logging.  
- Keep tests isolated and deterministic.

---

## Changelog

### v1.1.0 – 2026‑09‑04

- Added `file.upload-dir` property for configurable storage location.  
- Implemented error handling for uploads exceeding 500 MB.

### v1.0.0 – 2024‑01‑10

- Initial release: user management, file upload/download, H2 integration, dashboard.

---

## License

MIT – see the [LICENSE](LICENSE) file.
