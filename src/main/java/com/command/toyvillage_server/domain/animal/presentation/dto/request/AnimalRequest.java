package com.command.toyvillage_server.domain.animal.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record AnimalRequest(

        @NotBlank(message = "동물 종류를 비워둘 수 없습니다.")
        String animalKind,

        @NotBlank(message = "동물 사진을 첨부해주세요.")
        MultipartFile  animalImage,

        @NotBlank(message = "동물 소개를 비워둘 수 없습니다.")
        String animalDescription
) {
}
