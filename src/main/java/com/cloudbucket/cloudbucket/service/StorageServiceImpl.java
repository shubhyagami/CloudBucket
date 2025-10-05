package com.cloudbucket.cloudbucket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public StorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.rootLocation = Paths.get(uploadDir);
    }

    @PostConstruct
    public Path init() throws IOException {
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }
        return rootLocation;
    }

    @Override
    public Path store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            throw new IOException("Cannot store file with relative path outside current directory " + filename);
        }
        Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        try (java.io.InputStream input = file.getInputStream()) {
            Files.copy(input, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return destinationFile;
    }

    @Override
    public Resource loadAsResource(String filename) throws IOException {
        try {
            Path file = rootLocation.resolve(filename).normalize().toAbsolutePath();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IOException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new IOException("Could not read file: " + filename, e);
        }
    }
}
