package com.command.toyvillage_server.domain.animal.presentation.dto.response;

import com.command.toyvillage_server.domain.animal.domain.Animal;

public record AnimalResponse(
        String animalKind,
        String animalDescription
) {
    public static AnimalResponse from(Animal animal){
        return new AnimalResponse(
                animal.getAnimalKind(),
                animal.getAnimalDescription()
        );
    }
}
