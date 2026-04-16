package com.command.toyvillage_server.domain.gallery.service;

import com.command.toyvillage_server.domain.gallery.domain.Gallery;
import com.command.toyvillage_server.domain.gallery.domain.repository.GalleryRepository;
import com.command.toyvillage_server.domain.gallery.exception.GalleryNotFoundException;
import com.command.toyvillage_server.domain.gallery.presentation.dto.response.GalleryResponse;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryDetailGalleryService {
    private final GalleryRepository galleryRepository;
    private final AwsS3Provider awsS3Provider;

    @Transactional(readOnly = true)
    public GalleryResponse execute(Long galleryId) {
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> GalleryNotFoundException.EXCEPTION);

        String key = gallery.getFile().getFileKey();
        String presignedUrl = awsS3Provider.getPresignedUrl(key);

        return GalleryResponse.builder()
            .galleryId(galleryId)
            .galleryTitle(gallery.getTitle())
            .galleryFileUrl(presignedUrl)
            .build();
    }
}
