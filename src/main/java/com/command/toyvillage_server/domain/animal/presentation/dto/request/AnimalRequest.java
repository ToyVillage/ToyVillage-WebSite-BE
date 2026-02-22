package com.command.toyvillage_server.domain.animal.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AnimalRequest(

        @NotBlank(message = "동물 종류를 비워둘 수 없습니다.")
        String animalKind,

        @NotBlank(message = "동물 소개를 비워둘 수 없습니다.")
        String animalDescription
) {
}
