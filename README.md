# CloudBucket

A lightweight, self‑hosted cloud storage service built with Spring Boot.  
It offers secure authentication, file upload/download (up to 500 MB per file), and a minimalist dashboard – a handy sandbox for developers building Java web applications that need local storage.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/CloudBucket/build.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/CloudBucket/actions)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)](LICENSE)

---

## Table of Contents

1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Quick Start](#quick-start)
4. [Getting Started](#getting-started)
   * [Build & Run](#build--run)
   * [Docker](#docker)
   * [Access URLs](#access-urls)
5. [Configuration](#configuration)
6. [Running Tests](#running-tests)
7. [Contributing](#contributing)
8. [License](#license)
9. [Changelog](#changelog)

---

## Overview

CloudBucket is a Spring Boot application that stores files in a configurable local directory and uses an in‑memory H2 database by default, ideal for development. Features include:

* User management (signup, login, logout) with BCrypt password hashing.
* Web dashboard for uploading, downloading, and listing files.
* A pre‑configured development user (`admin`/`admin`) for quick testing.
* Extensible architecture: swap storage or authentication modules as needed.

---

## Key Features

| Feature | Description |
|---------|-------------|
| **Secure Auth** | BCrypt hashed passwords; session‑based login. |
| **File Management** | Upload, download, and delete files up to 500 MB; configurable storage location (`file.upload-dir`). |
| **Dashboard** | Responsive UI for file operations. |
| **Database** | In‑memory H2 for rapid startup; swap to any JDBC‑compatible DB. |
| **Extensibility** | Plug‑in custom storage or authentication providers. |

---

## Quick Start

```bash
# Clone
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket

# Build & run (skipping tests)
./mvnw -DskipTests package
./mvnw spring-boot:run
```

Open your browser at <http://localhost:8080/dashboard> and log in with the default credentials:

* **Username:** `admin`
* **Password:** `admin`

---

## Getting Started

### Build & Run

```bash
# Build the application
./mvnw clean package -DskipTests

# Run it
./mvnw spring-boot:run
```

The default port is `8080`. Change it with the `server.port` property.

### Docker

```bash
docker build -t cloudbucket .
docker run -p 8080:8080 \
  -e APP_USER_USERNAME=admin \
  -e APP_USER_PASSWORD=admin \
  -e FILE_UPLOAD_DIR=/data/uploads \
  cloudbucket
```

The container will expose the same endpoints on port `8080`.

### Access URLs

| Feature          | URL                              |
|------------------|----------------------------------|
| Signup           | `http://localhost:8080/signup`   |
| Login            | `http://localhost:8080/login`    |
| Dashboard        | `http://localhost:8080/dashboard` |
| H2 Console (dev) | `http://localhost:8080/h2-console`|

> **Tip:** The H2 console is only enabled in the `dev` profile. Activate it with `-Dspring.profiles.active=dev`.

---

## Configuration

Edit `src/main/resources/application.properties` or provide environment variables to override defaults.

```properties
# Development user (overridden by env vars)
app.user.username=admin
app.user.password=admin

# File storage location
file.upload-dir=${FILE_UPLOAD_DIR:/var/cloudbucket/uploads}

# Server
server.port=8080

# H2 console (only in dev)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Property | Description | Defaults |
|----------|-------------|----------|
| `app.user.username` | Default username | `admin` |
| `app.user.password` | Default password | `admin` |
| `file.upload-dir` | Directory to store uploaded files | `/var/cloudbucket/uploads` |
| `server.port` | HTTP port | `8080` |

Environment‑variable overrides are automatically mapped:

| Variable | Maps to |
|----------|--------|
| `APP_USER_USERNAME` | `app.user.username` |
| `APP_USER_PASSWORD` | `app.user.password` |
| `FILE_UPLOAD_DIR` | `file.upload-dir` |

---

## Running Tests

```bash
./mvnw test
```

All tests use the in‑memory H2 database. Coverage reports are generated in `target/site/`.

---

## Contributing

1. Fork the repo and create a descriptive branch.
2. Read the Javadoc and inline comments.
3. Run `./mvnw test` to confirm current behavior.
4. Add or update tests for new changes.
5. Push and open a pull request with a clear description.

### Code Style

* Follow standard Java and Spring conventions.
* Use Javadoc for public APIs.
* Keep console output clean; use logging instead.

---

## License

CloudBucket is distributed under the [MIT License](LICENSE).

---

## Changelog

### v1.1.0

* Added `file.upload-dir` property for configurable storage.
* Improved error handling for oversized uploads.

### v1.0.0

* Initial release: user management, file upload/download, H2 integration, dashboard.

---
