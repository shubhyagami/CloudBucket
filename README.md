[K[2m  [2mmodel z-ai/glm-5.3-flash failed, trying next...[0m[0m
# CloudBucket

A lightweight, self-hosted cloud storage backend for Spring Boot applications. CloudBucket ships with an admin dashboard, session-based authentication, and a configurable file storage area. Run it as a standalone service, or embed it into an existing Spring Boot project using the bundled starter.

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

## Features

- **Admin dashboard** — Upload, download, list, and delete files through a built-in web UI.
- **Session-based authentication** — Protect the dashboard with configurable credentials.
- **Configurable storage** — Store files anywhere on the host (default: `/var/cloudbucket/uploads`).
- **Upload limits** — 500 MB per file, with a clear error when the limit is exceeded.
- **Spring Boot starter** — Add CloudBucket to your own app with a single Maven or Gradle dependency.
- **Docker-ready** — Official image published on Docker Hub.
- **H2 console** — Embedded database console for local development and debugging.

## Requirements

- Java 21 or later
- Maven 3.9+ (or the included Maven wrapper, `./mvnw`)
- Docker (optional, for containerized deployment)

## Getting Started

Clone the repository and start the service:

```bash
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Then open <http://localhost:8080/dashboard> in your browser and sign in with the default credentials (`admin` / `admin`).

> **Note:** Change the default credentials before exposing the service on a network. See [Configuration](#configuration) for the relevant properties.

### Run in Development Mode

For local development with automatic restarts and the H2 console enabled:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The H2 console is available at <http://localhost:8080/h2-console>.

## Configuration

CloudBucket is configured through standard Spring Boot properties. The most commonly used settings:

| Property | Default | Description |
| --- | --- | --- |
| `cloudbucket.storage.path` | `/var/cloudbucket/uploads` | Directory where uploaded files are stored. |
| `cloudbucket.upload.max-file-size` | `500MB` | Maximum size per uploaded file. |
| `cloudbucket.auth.username` | `admin` | Dashboard login username. |
| `cloudbucket.auth.password` | `admin` | Dashboard login password. |

Override any of these in `application.yml`, `application.properties`, or via environment variables.

## Deployment

### Docker

The official image is published on Docker Hub:

```bash
docker run -d \
  --name cloudbucket \
  -p 8080:8080 \
  -v /var/cloudbucket/uploads:/var/cloudbucket/uploads \
  shubhyagami/cloudbucket:latest
```

### Build from Source

```bash
./mvnw clean package
java -jar target/cloudbucket-*.jar
```

## API Overview

The dashboard is backed by a small REST API. A few of the main endpoints:

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/files` | List stored files. |
| `POST` | `/api/files` | Upload a file. |
| `GET` | `/api/files/{id}` | Download a file. |
| `DELETE` | `/api/files/{id}` | Delete a file. |

All endpoints require an authenticated session.

## Using CloudBucket as a Dependency

Add the starter to an existing Spring Boot project:

**Maven**

```xml
<dependency>
  <groupId>io.github.shubhyagami</groupId>
  <artifactId>cloudbucket-spring-boot-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

**Gradle**

```groovy
implementation 'io.github.shubhyagami:cloudbucket-spring-boot-starter:1.0.0'
```

CloudBucket auto-configures itself when it is on the classpath. See [Configuration](#configuration) for available properties.

## Development

### Running Tests

```bash
./mvnw test
```

### Code Style

The project follows the standard Spring Java conventions. Run the formatter before submitting a pull request:

```bash
./mvnw spotless:apply
```

## Contributing

Issues and pull requests are welcome. For larger changes, please open an issue first to discuss the approach.

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/my-change`).
3. Commit your changes with a clear message.
4. Open a pull request.

## Changelog

### Unreleased
- Documentation cleanup and README restructuring.

See the [releases page](https://github.com/shubhyagami/CloudBucket/releases) for the full version history.

## License

Released under the [MIT License](LICENSE).
