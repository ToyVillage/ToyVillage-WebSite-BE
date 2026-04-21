package com.command.toyvillage_server.domain.animal.presentation.dto.response;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AnimalResponse(

        @JsonProperty("animal_kind")
        String animalKind,

        @JsonProperty("animal_image")
        String animalImage,

        @JsonProperty("animal_description")
        String animalDescription
) {
    public static AnimalResponse from(Animal animal){
        return new AnimalResponse(
                animal.getAnimalKind(),
                animal.getAnimalImage(),
                animal.getAnimalDescription()
        );
    }
}
