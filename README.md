# CloudBucket

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

> *"For all time. Always."* — The Time-Keepers would be pleased to know your files are securely stored along the Sacred Timeline.

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

All configuration is managed through `application.properties`. You can customize the file upload directory by setting the `file.upload-dir` property to your desired local path.

---

## 🕰️ Contributing (TVA Edition)

Welcome, variant! You’ve been recruited by the **Time Variance Authority** to help maintain the **Sacred Timeline** of CloudBucket. Any deviation from the codebase’s intended flow will be considered a **Nexus Event** and promptly **pruned**. Follow these guidelines to keep the timeline stable:

### 🔄 Pruning Process

1. **Check the Temporal Logs** – Search the issue tracker for existing tasks. Do not create duplicate anomalies.
2. **File a New Branch** – Name your branch with a valid temporal ID: `feature/<ticket-id>-<brief-description>` or `fix/<ticket-id>-<brief-description>`.
3. **Write Time-Stream Tests** – Every new feature or bug fix must be accompanied by a test that validates the timeline (unit tests are the Minutemen of code).
4. **Submit a Pull Request** – Request a review from a **TVA Analyst** (maintainer). Your PR will be inspected for **temporal inconsistencies** (merge conflicts, failing builds, missing docs).
5. **Await Judgment** – The Analyst will either **approve** (variant accepted) or **prune** (reject with reasoning). If pruned, you may resubmit after correcting the deviation.

### 🧑‍💻 Code of Conduct

- Do not create **temporal paradoxes** (e.g., breaking existing functionality without deprecation warnings).
- Keep the **Sacred Timeline** clean – no leftover debug logs, unused imports, or “TODO: fix later” comments.
- Respect the **Loki-approved** style: Java 21, Spring Boot 3.4.x, and 4-space indentation.
- If you discover a **time-slipping bug**, report it immediately as an issue with the `critical` label.

### 🎁 What You Get

- A **TVA Badge** (your name in the CONTRIBUTORS.md file)
- The satisfaction of preserving order across the multiverse
- Access to the **Timely Tea** (virtual coffee chat with maintainers)

---

*By contributing to CloudBucket, you agree to the **Temporal Code of Conduct** and acknowledge that any Nexus Events caused by your changes will be pruned without warning. Glory to the Time-Keepers!*

---

### Temporal Update

Auto-maintained entry for 2026-08-06 23:58 (CloudBucket).

---

### Temporal Update

Auto-maintained entry for 2026-08-06 23:59 (CloudBucket).

---

### Temporal Update

Auto-maintained entry for 2026-08-07 00:02 (CloudBucket).

---

### Temporal Update

Auto-maintained entry for 2026-08-07 08:00 (CloudBucket). Sacred Timeline scan complete. No Nexus events detected. README syntax stabilized for variants across all branching timelines.
