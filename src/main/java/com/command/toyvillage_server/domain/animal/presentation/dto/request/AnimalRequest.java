package com.command.toyvillage_server.domain.animal.presentation.dto.request;

import com.command.toyvillage_server.domain.animal.domain.enums.AnimalType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AnimalRequest(

        @NotBlank(message = "동물 이름을 비워둘 수 없습니다.")
        @JsonProperty("animal_kind")
        String animalKind,

        @NotNull(message = "동물 사진을 첨부해주세요.")
        @JsonProperty("animal_image")
        MultipartFile  animalImage,

        @NotBlank(message = "동물 소개를 비워둘 수 없습니다.")
        @JsonProperty("animal_description")
        String animalDescription,

        @NotNull(message = "동물 종류를 비워둘 수 없습니다.")
        @JsonProperty("animal_type")
        AnimalType animalType,

        @NotNull(message = "인기 동물 여부를 선택해주세요.")
        @JsonProperty("popular_animal")
        Boolean popularAnimal
) {
}
