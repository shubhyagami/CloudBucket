package com.cloudbucket.cloudbucket.repository;

import com.cloudbucket.cloudbucket.model.StoredFile;
import com.cloudbucket.cloudbucket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoredFileRepository extends JpaRepository<StoredFile, Long> {
    List<StoredFile> findAllByOwner(User owner);
}
