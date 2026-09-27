# CloudBucket

A lightweight, self-hosted cloud storage backend for Spring Boot applications. CloudBucket provides an admin dashboard, session-based authentication, and a configurable file storage area. It runs as a standalone service out of the box, or you can embed it into an existing Spring Boot project with the bundled starter.

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

- **Admin dashboard** – Upload, download, list, and delete files through a built-in web UI.
- **Session-based authentication** – Protect the dashboard with configurable credentials.
- **Configurable storage** – Store files anywhere on the host (default: `/var/cloudbucket/uploads`).
- **Upload limits** – 500 MB per file, with a clear error when the limit is exceeded.
- **Spring Boot starter** – Add CloudBucket to your own app with a single Maven/Gradle dependency.
- **Docker-ready** – Official image published on Docker Hub.
- **H2 console** – Embedded database console for local development and debugging.

## Requirements

- Java 21 or later
- Maven 3.9+ (or the included Maven wrapper, `./mvnw`)
- Docker (optional, for containerized deployment)

## Getting Started

### Run Locally with Maven

```bash
git clone https://github.com/shubhyagami/CloudBucket.git
cd CloudBucket
./mvnw spring-boot:run
```

Then open <http://localhost:8080/dashboard> in your browser and sign in with the default credentials (`admin` / `admin`).

> **Note:** Change the default credentials via `
