package com.command.toyvillage_server.domain.gallery.domain.repository;

import com.command.toyvillage_server.domain.gallery.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Query("select g from Gallery g join fetch g.file")
    List<Gallery> findAllWithFile();
}
