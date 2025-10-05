package com.cloudbucket.cloudbucket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "stored_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String contentType;
    private Long size;
    private String storagePath; // path on disk
    private Instant uploadedAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
