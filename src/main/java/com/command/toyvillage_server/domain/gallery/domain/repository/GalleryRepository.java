package com.command.toyvillage_server.domain.gallery.domain.repository;

import com.command.toyvillage_server.domain.gallery.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
