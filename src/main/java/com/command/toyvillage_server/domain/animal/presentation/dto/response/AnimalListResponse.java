package com.command.toyvillage_server.domain.animal.presentation.dto.response;

import java.util.List;

public record AnimalListResponse(
        List<AnimalResponse> popularAnimal,
        List<AnimalResponse> mammals,
        List<AnimalResponse> reptiles,
        List<AnimalResponse> fish,
        List<AnimalResponse> birds
) {
    public static AnimalListResponse from(
            List<AnimalResponse> popularAnimal,
            List<AnimalResponse> mammals,
            List<AnimalResponse> reptiles,
            List<AnimalResponse> fish,
            List<AnimalResponse> birds
    ) {
        return new AnimalListResponse(
                popularAnimal,
                mammals,
                reptiles,
                fish,
                birds
        );
    }
}
