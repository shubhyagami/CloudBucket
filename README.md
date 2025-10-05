# CloudBucket (simple)

This is a starter Spring Boot app that implements basic cloud-storage-like features:

- Signup and login (Spring Security + H2 DB)
- Dashboard listing uploaded files
- Upload single files (stored on local filesystem)
- Download files

Getting started

# CloudBucket (simple)

CloudBucket is a minimal Spring Boot demo that provides a simple personal cloud-storage experience.

Core features

- User signup and login (Spring Security + H2 in-memory database)
- Dashboard showing files uploaded by the authenticated user
- Upload single files (stored to a local directory configured by `file.upload-dir`)
- Download previously uploaded files
- Upload size limit: 500 MB (configured via Spring multipart properties)

Quick start

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

Default dev credentials

The application includes a simple `application.properties` development entry:

- username: `admin`
- password: `admin`

This user is present only for quick development. Use the signup page to create other users.

Configuration

All configuration is in `src/main/resources/application.properties`:

- `file.upload-dir` — directory where files are stored (defaults to `${user.home}/cloudbucket-uploads`)
- `spring.servlet.multipart.max-file-size` and `spring.servlet.multipart.max-request-size` — upload limits (set to `500MB`)

Important code pieces

- `com.cloudbucket.cloudbucket.model.User` — JPA entity for users
- `com.cloudbucket.cloudbucket.model.StoredFile` — file metadata
- `com.cloudbucket.cloudbucket.repository.*` — Spring Data repositories
- `com.cloudbucket.cloudbucket.service.StorageService`/`StorageServiceImpl` — stores files to disk and loads them as Resources
- `com.cloudbucket.cloudbucket.security.*` — Spring Security config and `UserDetailsService`
- `com.cloudbucket.cloudbucket.web.AuthController` — signup/login controllers
- `com.cloudbucket.cloudbucket.web.FileController` — dashboard, upload, download endpoints

How the upload limit works

- The app sets Spring multipart limits to `500MB`. If a single file or the total request exceeds that, the server will reject the upload.
- `FileController` also checks the file size before attempting storage and returns a friendly flash message if the file is too large.
- A `ControllerAdvice` (`GlobalExceptionHandler`) catches `MaxUploadSizeExceededException` and redirects with a user message.

Security notes

- CSRF protection is enabled. All POST forms include CSRF tokens.
- For production, disable H2 console, secure configuration, and replace the in-memory DB with a persistent DB.

Next steps / recommended improvements

- Integrate cloud object storage (S3/Azure Blob) by implementing `StorageService` for remote storage.
- Add delete/rename and file metadata editing.
- Add multi-file uploads, progress bars, and resumable uploads for large files.
- Add per-user storage quotas and usage reporting.
- Add unit and integration tests for controllers and services.

Troubleshooting

- If uploads fail with a CSRF or 403, ensure the form includes the CSRF token (the templates include these by default).
- If the app won't start, check the terminal log for stack traces and post them.

License & credits

This is sample/demo code. Adapt it as needed for your project and apply appropriate security, storage, and operational hardening before production use.

Deploy to Render
-----------------

This project includes a `Dockerfile` so it can be deployed to Render as a Docker web service (recommended). There are two easy options:

1) Docker (recommended)

- Render will build the image using the included `Dockerfile` (multi-stage build). The Dockerfile runs the Maven build inside a Maven/JDK image and then packages the app into a lightweight JRE image.
- When creating the Render service, choose `Docker` for the environment so Render uses the Dockerfile. No Start Command is required (the Dockerfile's CMD will be used).

2) Native Render Web Service (no Docker)

- If you prefer Render to build the project, use the following settings in the Render Web Service configuration:
	- Build Command: `./mvnw -DskipTests package`
	- Start Command: `java -Dserver.port=$PORT -jar target/*.jar`
- Important: `mvnw` must be executable on the Linux build image. I fixed this by setting the executable bit in the repo (`git update-index --chmod=+x mvnw`).

Notes & tips

- `server.port` binding: start command above uses `-Dserver.port=$PORT` so your app listens on the Render-provided port. You can also add `server.port=${PORT:8080}` to `src/main/resources/application.properties` as a fallback.
- `application.properties` is included in the Docker build. If you use the Docker option, uploaded files saved to `file.upload-dir` are stored in the container filesystem and may be lost on redeploy — use S3 or Render persistent disks for production storage.
- If builds fail on Render with `Permission denied` or `JAVA_HOME` errors, prefer Docker or check the build image configuration.

Quick checklist before deploying

1. Ensure `mvnw` is executable (commit pushed) — already done in this repo.
2. Ensure `application.properties` is present in the repo (not excluded by `.dockerignore`) — fixed.
3. Push your changes to GitHub and create a new Render Web Service pointing at this repository.

Troubleshooting Render

- Check Render build and live logs for errors. Common failures and solutions:
	- `./mvnw: Permission denied` → make `mvnw` executable (done).
	- `The JAVA_HOME environment variable is not defined correctly` → use Docker or set JAVA_HOME in Render build environment (less reliable than Docker).
	- `Could not resolve placeholder 'file.upload-dir'` → ensure `application.properties` or required env vars are present.

