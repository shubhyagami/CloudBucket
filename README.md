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

- The app sets Spring multipart limits to `500MB`. If

---

## Pro Tips

- **Name your files wisely**: Use descriptive, unique filenames before uploading — the stored file retains the original name, making downloads instantly recognizable.
- **Keep uploads under 500 MB**: Although the limit is generous, splitting very large files (e.g., videos) into parts avoids timeouts and makes retrieval easier.
- **Use the admin account for testing only**: Create separate users via the signup page to simulate multi‑user behavior and avoid accidental data sharing.
- **Check the upload directory regularly**: Files are stored locally in the configured `file.upload-dir`. Periodically clean up or back up this folder as needed.

---

## Changelog

### 2026-08-01
- **Enhanced README**: Added Pro Tips section and this Changelog entry to improve developer onboarding and documentation clarity.
- No functional changes to the application.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.