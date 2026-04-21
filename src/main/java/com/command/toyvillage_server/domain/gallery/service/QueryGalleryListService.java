package com.command.toyvillage_server.domain.gallery.service;

import com.command.toyvillage_server.domain.gallery.domain.repository.GalleryRepository;
import com.command.toyvillage_server.domain.gallery.presentation.dto.response.GalleryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryGalleryListService {
    private final GalleryRepository galleryRepository;

    @Transactional(readOnly = true)
    public Page<GalleryResponse> execute(Pageable p) {
        Pageable pageable = PageRequest.of(
            p.getPageNumber(),
            p.getPageSize(),
            Sort.by(Sort.Direction.DESC, "id")
        );

        return galleryRepository.findAllWithFile(pageable)
            .map(GalleryResponse::from);
    }
}
