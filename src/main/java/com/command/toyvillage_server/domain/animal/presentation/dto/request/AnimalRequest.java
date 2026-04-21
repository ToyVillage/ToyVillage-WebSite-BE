package com.command.toyvillage_server.domain.animal.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record AnimalRequest(

        @NotBlank(message = "동물 종류를 비워둘 수 없습니다.")
        @JsonProperty("animal_kind")
        String animalKind,

        @NotBlank(message = "동물 사진을 첨부해주세요.")
        @JsonProperty("animal_image")
        MultipartFile  animalImage,

        @NotBlank(message = "동물 소개를 비워둘 수 없습니다.")
        @JsonProperty("animal_description")
        String animalDescription
) {
}
