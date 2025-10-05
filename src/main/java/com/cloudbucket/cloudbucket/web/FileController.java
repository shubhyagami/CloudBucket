package com.cloudbucket.cloudbucket.web;

import com.cloudbucket.cloudbucket.model.StoredFile;
import com.cloudbucket.cloudbucket.model.User;
import com.cloudbucket.cloudbucket.repository.StoredFileRepository;
import com.cloudbucket.cloudbucket.repository.UserRepository;
import com.cloudbucket.cloudbucket.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Controller
public class FileController {

    private final StorageService storageService;
    private final StoredFileRepository storedFileRepository;
    private final UserRepository userRepository;

    private final long maxUploadBytes;

    public FileController(StorageService storageService, StoredFileRepository storedFileRepository, UserRepository userRepository,
                          @Value("${spring.servlet.multipart.max-file-size}") String maxFileSize) {
        this.storageService = storageService;
        this.storedFileRepository = storedFileRepository;
        this.userRepository = userRepository;
        // parse human-readable size like 500MB -> bytes
        this.maxUploadBytes = parseSizeToBytes(maxFileSize);
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model, Authentication auth) {
        String username = auth.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return "redirect:/login";
        User user = userOpt.get();
        List<StoredFile> files = storedFileRepository.findAllByOwner(user);
        model.addAttribute("files", files);
        return "dashboard";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication auth, RedirectAttributes redirectAttributes) throws IOException {
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        if (file == null || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "redirect:/dashboard";
        }
        if (file.getSize() > maxUploadBytes) {
            redirectAttributes.addFlashAttribute("error", "File exceeds maximum allowed size of 500MB.");
            return "redirect:/dashboard";
        }
        Path stored = storageService.store(file);

        StoredFile sf = new StoredFile();
        sf.setFilename(file.getOriginalFilename());
        sf.setContentType(file.getContentType());
        sf.setSize(file.getSize());
        sf.setStoragePath(stored.toString());
        sf.setOwner(user);
        storedFileRepository.save(sf);
        redirectAttributes.addFlashAttribute("message", "Uploaded: " + sf.getFilename());
        return "redirect:/dashboard";
    }

    private long parseSizeToBytes(String size) {
        if (size == null) return 0L;
        String s = size.trim().toUpperCase();
        try {
            if (s.endsWith("MB")) {
                return Long.parseLong(s.replace("MB", "")) * 1024L * 1024L;
            } else if (s.endsWith("KB")) {
                return Long.parseLong(s.replace("KB", "")) * 1024L;
            } else if (s.endsWith("GB")) {
                return Long.parseLong(s.replace("GB", "")) * 1024L * 1024L * 1024L;
            } else {
                return Long.parseLong(s);
            }
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    @GetMapping("/files/{id}/download")
    public org.springframework.http.ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
        StoredFile sf = storedFileRepository.findById(id).orElseThrow();
        Resource resource = storageService.loadAsResource(sf.getFilename());
        String encoded = URLEncoder.encode(sf.getFilename(), StandardCharsets.UTF_8);
        return org.springframework.http.ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encoded)
                .contentLength(sf.getSize() == null ? resource.contentLength() : sf.getSize())
                .contentType(org.springframework.http.MediaType.parseMediaType(Optional.ofNullable(sf.getContentType()).orElse("application/octet-stream")))
                .body(resource);
    }
}
