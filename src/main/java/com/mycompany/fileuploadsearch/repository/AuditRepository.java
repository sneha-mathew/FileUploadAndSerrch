package com.mycompany.fileuploadsearch.repository;

import co.elastic.clients.elasticsearch.xpack.usage.Audit;
import com.mycompany.fileuploadsearch.entity.File;
import com.mycompany.fileuploadsearch.enums.ActionType;
import com.mycompany.fileuploadsearch.enums.FileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuditRepository {
    @Query("SELECT a FROM File_Audit a INNER JOIN File f on f.id= a.file_id " +
            "WHERE f.filename LIKE %:filename% AND a.action = :actionType  AND " +
            "a.auditDate BETWEEN :startDate AND :endDate")
    Page<Audit> findFiles(String filename, ActionType actionType, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Audit save(Audit audit);
    Page<Audit> findByFileId(Long fileId, Pageable pageable);
}
