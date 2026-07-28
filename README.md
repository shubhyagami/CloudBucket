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
- A `ControllerAd

---

## Pro Tips

🪣 **Keep your bucket tidy** – Regularly review and delete files you no longer need via the dashboard. This keeps storage lean and your uploads snappy.

🔐 **Use strong passwords** – Even though this is a demo, get in the habit of creating unique, complex passwords for each user. Your future self (and your files) will thank you.

📁 **Organize by naming convention** – Prefix file names with a date or category (e.g., `2026-07-28_report.pdf`) to make them easier to find later. The dashboard sorts alphabetically, so a little structure goes a long way.

🚀 **Extend with thumbnails** – Want to preview images? Add a service that generates small previews on upload. It’s a great next step for your own cloud‑storage playground.

⚡ **Hot‑reload config** – While running in dev mode, you can change `file.upload-dir` in `application.properties` and restart the context (or the app) to point uploads to a different folder. Handy for testing with multiple directories.

---

*Happy cloud‑bucketing!* 🌥️