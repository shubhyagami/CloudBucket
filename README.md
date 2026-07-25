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
- A `ControllerAdvice` (`GlobalExceptionHandler`) catches `MaxUploadSizeExceededException` and redirects with a user message.

---

## Security notes

- CSRF protection is enabled. All POST forms include CSRF tokens.
- For production, disable H2 console, secure configuration, and replace the in-memory DB with a persistent DB.

---

## Pro Tips ⚡

1. **Local storage vs. cloud** – The demo stores files on disk. To make it production‑ready, swap `StorageServiceImpl` for an S3‑compatible client (e.g., AWS S3, MinIO).
2. **Persist users** – Replace H2 with PostgreSQL or MySQL, and switch from `spring.jpa.hibernate.ddl-auto=update` to managed migrations (Flyway / Liquibase).
3. **Secure file names** – `StoredFile` stores the original name, but the disk file uses a UUID to prevent path‑traversal attacks. Always validate file extensions on upload.
4. **Rate limiting** – Add a `Filter` or Spring Cloud Gateway to throttle upload requests per user (e.g., 10 files/minute).
5. **Unit test the service** – Use `@SpringBootTest` with an in‑memory file system (like Jimfs) to avoid polluting your local disk during tests.

---

## Weekly Highlight 🗓️

**This week’s focus: Upload resilience**  
The app now gracefully handles `MaxUploadSizeExceededException` and returns a flash message instead of a raw 500. Next up: chunked uploads for large files (>500 MB).

---

## Changelog – 2026-07-26

- Added `GlobalExceptionHandler` to catch upload size exceptions and redirect with a friendly message.
- Enhanced file size validation in `FileController` before persisting metadata.
- Updated README with badges, ASCII art header, and Pro Tips section.
- Refactored `StorageServiceImpl` to use `Path` instead of `File` for better NIO integration.

---

## Motivational Quote 💡

> *"The cloud is not a place; it’s a way of thinking. Start small, store safely, and never stop scaling."*  
> — TVA Temporal Engineer’s Daily Affirmation

---

## Next steps

- Add unit/integration tests for the storage layer.
- Implement file deletion and sharing via signed URLs.
- Containerize with Docker and add a `docker-compose.yml`.
- Add a `DELETE /files/{id}` endpoint.

---

*Maintained by [shubhyagami](https://github.com/shubhyagami) · TVA Temporal Engineering Division*