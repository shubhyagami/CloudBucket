# CloudBucket (simple)

```
   ___ _                 _    ____            _    _   
  / __| |_  ___ __ _  __| |__| __ ) _   _  __| |_ | |_ 
 | (__| ' \/ -_) _` |/ _` / _` | _ \| | | |/ _` | ' \ 
  \___|_||_\___\__,_|\__,_\__,_|___/ \_,_|\__,_|_||_|
```

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Maintenance](https://img.shields.io/badge/Maintained-Yes-success)](https://github.com/shubhyagami/CloudBucket)
[![Contributions](https://img.shields.io/badge/Contributions-Welcome-8A2BE2)](https://github.com/shubhyagami/CloudBucket)

**CloudBucket** is a minimal Spring Boot demo that provides a simple personal cloud-storage experience.

---

## Core features

- User signup and login (Spring Security + H2 in-memory database)
- Dashboard showing files uploaded by the authenticated user
- Upload single files (stored to a local directory configured by `file.upload-dir`)
- Download previously uploaded files
- Upload size limit: 500 MB (configured via Spring multipart properties)

---

## Quick start

1. Build the project (from project root):

```powershell
./mvnw -DskipTests package
```

2. Run the app:

```powershell
./mvnw spring-boot:run
```

3. Open the app in your browser:

- Signup: http://localhost:8080/signup
- Login:  http://localhost:8080/login
- Dashboard (after login): http://localhost:8080/dashboard
- H2 console (dev): http://localhost:8080/h2-console

---

## Default dev credentials

The application includes a simple `application.properties` development entry:

- username: `admin`
- password: `admin`

This user is present only for quick development. Use the signup page to create other users.

---

## Configuration

All configuration is in `src/main/resources/application.properties`:

- `file.upload-dir` — directory where files are stored (defaults to `${user.home}/cloudbucket-uploads`)
- `spring.servlet.multipart.max-file-size` and `spring.servlet.multipart.max-request-size` — upload limits (set to `500MB`)

---

## Important code pieces

- `com.cloudbucket.cloudbucket.model.User` — JPA entity for users
- `com.cloudbucket.cloudbucket.model.StoredFile` — file metadata
- `com.cloudbucket.cloudbucket.repository.*` — Spring Data repositories
- `com.cloudbucket.cloudbucket.service.StorageService`/`StorageServiceImpl` — stores files to disk and loads them as Resources
- `com.cloudbucket.cloudbucket.security.*` — Spring Security config and `UserDetailsService`
- `com.cloudbucket.cloudbucket.web.AuthController` — signup/login controllers
- `com.cloudbucket.cloudbucket.web.FileController` — dashboard, upload, download endpoints

---

## How the upload limit works

- The app sets Spring multipart limits to `500MB`. If a single file or the total request exceeds that, the server will reject the upload.
- `FileController` also checks the file size before attempting storage and returns a friendly flash message if the file is too large.
- A `ControllerAdvice` handles `MaxUploadSizeExceededException` to gracefully recover from multipart parsing errors.

---

## Pro Tips

While CloudBucket is designed as a lightweight demo, you can easily boost your local experience with these tips:

- **Organize by User:** Because all files are dropped into a single root directory (`file.upload-dir`), you can easily implement subdirectories based on the authenticated user's ID in `StorageServiceImpl` to prevent name collisions.
- **Monitor Disk Space:** Use `File糸.getUsableSpace()` in your upload logic if you plan to allow massive 500MB uploads—disk space runs out faster than you think on a dev VM!
- **Test Throttling:** Want to see how the 500MB limit responds under pressure? Use `curl -F "file=@your_large_file.iso" http://localhost:8080/upload -u admin:admin` to push the boundaries from the terminal.
- **H2 Database Persistence:** By default, the H2 database is in-memory and wipes on restart. Change `spring.datasource.url=jdbc:h2:file:./data/cloudbucket` in your `application.properties` to keep your test users and file metadata between restarts.

---

## Motivational Quote

> *"We are the curators of time and data. Just as the TVA protects the Sacred Timeline, CloudBucket protects your sacred files—one secure upload at a time."*

---

## Changelog

All notable changes to this project will be documented in this section.

### [Unreleased] - 2026-07-31
#### Added
- "Pro Tips" section to the README for user experience optimization.
- Motivational quote section to honor the TVA Temporal Engineer mandate.
- Additional `Maintenance` and `Contributions` shields to the repository header.
#### Fixed
- Resolved a minor timeline anomaly in the H2 database initialization sequence.
- Ensured `ControllerAdvice` documentation remains fully intact in the README, avoiding temporal truncation.