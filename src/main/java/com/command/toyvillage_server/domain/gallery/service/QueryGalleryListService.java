package com.command.toyvillage_server.domain.gallery.service;

import com.command.toyvillage_server.domain.gallery.domain.repository.GalleryRepository;
import com.command.toyvillage_server.domain.gallery.presentation.dto.response.GalleryResponse;
import com.command.toyvillage_server.global.aws.s3.AwsS3Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryGalleryListService {
    private final GalleryRepository galleryRepository;
    private final AwsS3Provider awsS3Provider;

    @Transactional(readOnly = true)
    public List<GalleryResponse> execute() {
        return galleryRepository.findAll().stream()
                .map(gallery -> GalleryResponse.builder()
                        .galleryId(gallery.getId())
                        .galleryTitle(gallery.getTitle())
                        .galleryFileUrl(awsS3Provider.getPresignedUrl(gallery.getFile().getFileKey()))
                        .build())
                .toList();
    }
}
