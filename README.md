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

## Key Features

- Secure authentication
- User dashboard
- File management (upload and download up to 500 MB)
- Configurable local storage
- H2 database for effortless development

## Getting Started

To start using CloudBucket, follow these simplified setup instructions:

### Build and Run

1. **Build the Project**: Run `./mvnw -DskipTests package` in the root directory.
2. **Run the Application**: Execute `./mvnw spring-boot:run`.
3. **Access the Application**: Visit the following URLs in your browser:
   - **Signup**: `http://localhost:8080/signup`
   - **Login**: `http://localhost:8080/login`
   - **Dashboard**: `http://localhost:8080/dashboard`
   - **H2 Console**: `http://localhost:8080/h2-console`

## Configuration

Customize your application settings in `src/main/resources/application.properties`.

### Default Development User

A pre-configured development user is provided:

- **Username**: `admin`
- **Password**: `admin`

Create a custom user by visiting the signup page.

### File Upload Directory

Customize the local file storage directory by modifying the `file.upload-dir` property in `application.properties`.

## Contributing

If you'd like to help improve or expand CloudBucket, please follow these guidelines:

1. **Check existing issues**: Review the issue tracker to avoid duplicate submissions.
2. **Branching**: Use descriptive branch names for your code contributions.
3. **Testing**: Include relevant unit tests for new features or bug fixes.
4. **Pull Requests**: Submit a pull request with style consistency and minimal debug logs.

## License

CloudBucket is distributed under the MIT License. For further details, refer to the `LICENSE` file.

## Changelog

No notable changes were made for this version. For the most up-to-date information, please visit the project's GitHub page.
