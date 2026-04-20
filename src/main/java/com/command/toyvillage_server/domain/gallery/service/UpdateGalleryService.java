package com.command.toyvillage_server.domain.gallery.service;

import com.command.toyvillage_server.domain.file.domain.File;
import com.command.toyvillage_server.domain.file.domain.repository.FileRepository;
import com.command.toyvillage_server.domain.file.exception.FileNotFoundException;
import com.command.toyvillage_server.domain.gallery.domain.Gallery;
import com.command.toyvillage_server.domain.gallery.domain.repository.GalleryRepository;
import com.command.toyvillage_server.domain.gallery.exception.GalleryNotFoundException;
import com.command.toyvillage_server.domain.gallery.presentation.dto.request.GalleryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateGalleryService {
    private final GalleryRepository galleryRepository;
    private final FileRepository fileRepository;

    @Transactional
    public void execute(GalleryRequest request, Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> GalleryNotFoundException.EXCEPTION);
        File file = fileRepository.findByFileKey(request.getFileKey()).orElseThrow(() -> FileNotFoundException.EXCEPTION);

        gallery.update(
            request.getGalleryTitle(),
            file
        );
    }
}
