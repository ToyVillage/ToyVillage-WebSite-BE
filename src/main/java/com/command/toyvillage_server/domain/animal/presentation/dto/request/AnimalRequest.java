package com.command.toyvillage_server.domain.animal.presentation.dto.request;

public record AnimalRequest(
        String animalKind,
        String animalDescription
) {
}
