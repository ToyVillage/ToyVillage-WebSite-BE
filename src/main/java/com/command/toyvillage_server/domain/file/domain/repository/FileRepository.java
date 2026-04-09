package com.command.toyvillage_server.domain.file.domain.repository;

import com.command.toyvillage_server.domain.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByFileKey(String key);
}
