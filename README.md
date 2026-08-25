# CloudBucket
-------------
```markdown
   ___ _                 _    ____            _    _   
  / __| |_  ___ __ _  __| |__| __ ) _   _  __| |_ | |_ 
 | (__| ' \/ -_) _` |/ _` / _` | _ \ | | |/ _` | ' \ 
  \___|_||_\___\__,_|\__,_\__,_|___/ \_,_|\__,_|_||_|
```

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/CloudBucket)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-blue)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Database](https://img.shields.io/badge/Database-H2-blueviolet)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)
[![Maintained](https://img.shields.io/badge/Maintained-Yes-success)](https://github.com/shubhyagami/CloudBucket)

A personal cloud storage experience, simplified using Spring Boot.

## Overview

CloudBucket is a personal cloud storage platform built on top of Spring Boot, designed to provide a secure and user-friendly experience for storing and managing files.

## Features

- **Secure Authentication**: Protect your data with Spring Security.
- **User Dashboard**: A secure space for file management, accessible only to each user.
- **File Management**: Upload and download files up to 500 MB.
- **Configurable Local Storage**: Save your files locally, with the directory determined by your application properties.
- **H2 Database**: Get started quickly with an in-memory database for effortless development.

## Getting Started

To start using CloudBucket locally, follow these simplified setup instructions.

### Build and Run

1. **Build the Project**: From the root directory, use:
   ```bash
   ./mvnw -DskipTests package
   ```
2. **Run the Application**: Execute:
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Access the Application**: Visit the following URLs in your browser to begin exploring:
   - **Signup**: `http://localhost:8080/signup`
   - **Login**: `http://localhost:8080/login`
   - **Dashboard**: `http://localhost:8080/dashboard`
   - **H2 Console**: `http://localhost:8080/h2-console`

## Configuration

Control and customize your application settings through `src/main/resources/application.properties`.

### Default Development User

A pre-configured development user is provided to save time:

- **Username**: `admin`
- **Password**: `admin`

For adding custom users, visit the signup page.

### File Upload Directory

Customize the local file storage directory by modifying the `file.upload-dir` property in `application.properties`.

## Contributing

If you'd like to help improve or expand CloudBucket, please follow these guidelines:

1. **Check existing issues**: Avoid duplicate submissions by reviewing the issue tracker.
2. **Branching**: Use descriptive branch names for your code contributions (e.g., `feature/add-file-preview` or `fix/login-redirect`).
3. **Testing**: Include relevant unit tests for new features or bug fixes.
4. **Pull Requests**: For code review, submit a pull request with style consistency (Java 21, Spring Boot 3.4.x, 4-space indentation) and minimal debug logs or unused imports.

## License

CloudBucket is distributed under the MIT License. For further details, refer to the `LICENSE` file.
