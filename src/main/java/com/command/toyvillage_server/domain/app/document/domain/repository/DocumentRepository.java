package com.command.toyvillage_server.domain.app.document.domain.repository;

import com.command.toyvillage_server.domain.app.document.domain.Document;
import com.command.toyvillage_server.domain.app.document.domain.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> getAllByTitleContainsIgnoreCase(String title, Pageable pageable);

    Page<Document> getAllByTitleContainsIgnoreCaseAndTypeIn(String title, List<DocumentType> types, Pageable pageable);
}
