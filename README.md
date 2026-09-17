# CloudBucket

**CloudBucket** is a lightweight, self‑hosted cloud‑storage backend for Spring Boot applications.  
It ships with a ready‑to‑use dashboard, session‑based authentication, and configurable file storage.

> **Default credentials:** `admin / admin`  
> Override them with environment variables or `application.properties`.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)  
![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)  
![Maven](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)  
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)  
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Build & Run](#build--run)
  - [Docker](#docker)
  - [Quick Start](#quick-start)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [Using the Starter in Your Project](#using-the-starter-in-your-project)
- [Testing](#testing)
- [Contributing](#contributing)
- [Changelog](#changelog)
- [License](#license)

---

## Getting Started

### Prerequisites

| Tool  | Minimum version |
|-------|-----------------|
| Java  | 21              |
| Maven | 3.9.6 (or `./mvnw`) |
| Docker| optional – for containerized deployment |

### Build & Run

```bash
# 1. Clone the repository
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# 2. Build the application (skip tests for speed)
./mvnw -DskipTests package

# 3. Run
./mvnw spring-boot:run
```

Open <http://localhost:8080/dashboard> and log in with the default credentials.  
File uploads are limited to 500 MB by default.

#### Development Mode

Enable the H2 console for easier debugging:

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

The `-v` flag preserves uploaded files across container restarts.

### Quick Start

If you prefer to run the application directly without the Maven wrapper:

```bash
java -jar target/cloudbucket-1.1.0.jar
```

The default configuration starts the application on port `8080` with the assumptions described above.

---

## Configuration

CloudBucket reads `application.properties` (or `application.yml`).  
Environment variables can override any property; the mapping rule is:

```
property.name  ->  PROPERTY_NAME
```

| Property                     | Default                        | Description                                 |
|------------------------------|--------------------------------|---------------------------------------------|
| `app.user.username`         | `admin`                        | Default login username                      |
| `app.user.password`          | `admin`                        | Default login password                      |
| `file.upload-dir`            | `/var/cloudbucket/uploads`     | Directory where uploaded files are stored   |
| `server.port`                | `8080`                         | HTTP port                                    |
| `spring.h2.console.enabled`  | `false`                        | Enable H2 console (dev profile)             |
| `spring.h2.console.path`      | `/h2-console`                   | H2 console base path                        |

Example environment variable mappings:

| Variable              | Property               |
|------------------------|------------------------|
| `APP_USER_USERNAME`    | `app.user.username`    |
| `APP_USER_PASSWORD`    | `app.user.password`    |
| `FILE_UPLOAD_DIR`      | `file.upload-dir`      |
| `SERVER_PORT`          | `server.port`          |

---

## Endpoints

| Path            | Purpose                                        |
|-----------------|------------------------------------------------|
| `/dashboard`    | File‑management UI (upload / download)          |
| `/signup`       | Register a new user (optional)                   |
| `/login`        | User login                                   |
| `/logout`       | User logout                                   |
| `/h2-console`   | H2 console (available only with `dev` profile) |

---

## Using the Starter in Your Project

Add the starter dependency to your **pom.xml**:

```xml
<dependency>
  <groupId>com.github.shubhyagami</groupId>
  <artifactId>cloudbucket-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

You can customize the default authentication or storage by declaring beans of the same type.  
The starter will auto‑configure the remaining infrastructure.

---

## Testing

Run the complete test suite against an in‑memory H2 database:

```bash
./mvnw test
```

Coverage reports are generated in `target/site/`.

---

## Contributing

1. Fork the repository and create a feature branch.  
2. Run `./mvnw test` to confirm the build passes.  
3. Add or update tests for any new behaviour.  
4. Submit a pull request with a clear description of the changes.

### Coding Guidelines

- Follow standard Java and Spring conventions.  
- Document public APIs with Javadoc.  
- Use SLF4J for logging.  
- Keep tests deterministic and isolated.

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
