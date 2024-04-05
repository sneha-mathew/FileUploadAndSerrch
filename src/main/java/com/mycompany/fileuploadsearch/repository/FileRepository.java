package com.mycompany.fileuploadsearch.repository;

import com.mycompany.fileuploadsearch.entity.File;
import com.mycompany.fileuploadsearch.enums.FileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    @Query("SELECT f FROM File f WHERE f.filename LIKE %:filename% AND f.fileType = :fileType AND f.isDeleted = false AND " +
            "f.uploadDateTime BETWEEN :startDate AND :endDate")
    Page<File> findFiles(String filename, FileType fileType, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    File save(File file);
}
