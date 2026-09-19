[K[2m  [2mmodel openai/gpt-oss-20b failed, trying next...[0m[0m
[K[2m  [2mmodel openai/gpt-oss-120b failed, trying next...[0m[0m
# CloudBucket

**CloudBucket** is a lightweight, self-hosted cloud storage backend designed for Spring Boot applications. It provides a ready-to-use management dashboard, session-based authentication, and a highly configurable file storage system.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)
![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.9.6-brightgreen?style=flat-square)
![Docker Pulls](https://img.shields.io/docker/pulls/shubhyagami/cloudbucket.svg?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

### Key Features
- **Built-in Dashboard**: Simple UI for uploading and managing files.
- **Secure Access**: Session-based authentication with customizable credentials.
- **Flexible Storage**: Configure where your files are stored on the host system.
- **Spring Boot Starter**: Easily integrate CloudBucket functionality into your own projects.
- **Containerized**: Docker support for rapid deployment.

---

## Table of Contents
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Integration Guide](#integration-guide)
- [Testing](#testing)
- [Contributing](#contributing)
- [Changelog](#changelog)
- [License](#license)

---

## Getting Started

### Prerequisites
| Tool | Minimum Version | Note |
| :--- | :--- | :--- |
| **Java** | 21 | Required for runtime and build |
| **Maven** | 3.9.6 | Or use the provided `./mvnw` wrapper |
| **Docker** | Latest | Optional (for containerized deployment) |

### Local Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/shubhyagami/CloudBucket.git
   cd CloudBucket
   ```

2. **Build the project**
   ```bash
   ./mvnw clean package -DskipTests
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

Access the dashboard at `http://localhost:8080/dashboard`.  
**Default Credentials:** `admin` / `admin`

#### Development Mode
To enable the H2 database console for debugging:
```bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

### Docker Deployment
Build and run the container using the following commands:

```bash
# Build the image
docker build -t cloudbucket .

# Run the container
docker run -d \
  -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  -v /your/host/path:/data/uploads \
  cloudbucket
```
*Note: The `-v` volume mapping ensures your uploaded files persist across container restarts.*

---

## Configuration

CloudBucket can be configured via `application.properties`, `application.yml`, or environment variables.

### Property Mapping
Environment variables follow the standard Spring Boot relaxation rule: `property.name` $\rightarrow$ `PROPERTY_NAME`.

| Property | Env Variable | Default | Description |
| :--- | :--- | :--- | :--- |
| `app.user.username` | `APP_USER_USERNAME` | `admin` | Admin login username |
| `app.user.password` | `APP_USER_PASSWORD` | `admin` | Admin login password |
| `file.upload-dir` | `FILE_UPLOAD_DIR` | `/var/cloudbucket/uploads` | Path to store uploaded files |
| `server.port` | `SERVER_PORT` | `8080` | Server HTTP port |
| `spring.h2.console.enabled`| `SPRING_H2_CONSOLE_ENABLED`| `false` | Enable H2 console (dev profile) |

---

## API Endpoints

| Path | Method | Description |
| :--- | :--- | :--- |
| `/dashboard` | `GET` | File management UI (Upload/Download) |
| `/login` | `POST` | Authenticate user session |
| `/logout` | `POST` | Terminate user session |
| `/signup` | `POST` | Register a new user (if enabled) |
| `/h2-console` | `GET` | Database console (Dev profile only) |

---

## Integration Guide

To use CloudBucket as a library in your own Spring Boot project, add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.shubhyagami</groupId>
  <artifactId>cloudbucket-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

**Customization:**  
You can override the default authentication or storage logic by declaring your own beans of the same type in your application context. The starter will handle the rest of the auto-configuration.

---

## Testing

The project uses an in-memory H2 database for testing to ensure isolation.

```bash
./mvnw test
```
Detailed coverage reports are available in `target/site/` after the test run.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository and create your feature branch.
2. Ensure all tests pass by running `./mvnw test`.
3. Add new tests for any new functionality.
4. Submit a Pull Request with a clear description of your changes.

### Guidelines
- Follow standard Java and Spring Boot coding conventions.
- Use SLF4J for all logging.
- Document public APIs using Javadoc.
- Ensure tests remain deterministic.

---

## Changelog

### v1.1.0 (2026-09-04)
- Introduced `file.upload-dir` for configurable storage locations.
- Added validation and error handling for file uploads exceeding 500MB.

### v1.0.0 (2024-01-10)
- Initial release.
- Core features: User management, File I/O, H2 integration, and Admin Dashboard.

---

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
