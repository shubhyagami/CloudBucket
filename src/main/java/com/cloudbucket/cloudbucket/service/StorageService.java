package com.cloudbucket.cloudbucket.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    Path init() throws IOException;
    Path store(MultipartFile file) throws IOException;
    Resource loadAsResource(String filename) throws IOException;
}
