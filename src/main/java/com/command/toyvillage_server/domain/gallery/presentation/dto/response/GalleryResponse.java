package com.command.toyvillage_server.domain.gallery.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GalleryResponse (
    @JsonProperty("gallery_id")
    Long galleryId,
    @JsonProperty("gallery_title")
    String galleryTitle,
    @JsonProperty("gallery_file_key")
    String galleryFileKey
) { }
