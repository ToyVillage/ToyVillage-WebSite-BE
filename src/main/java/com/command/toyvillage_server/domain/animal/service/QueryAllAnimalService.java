package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.enums.AnimalType;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalListResponse;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllAnimalService {
    private final AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public AnimalListResponse execute() {
        List<Animal> animals = animalRepository.findAll();

        List<AnimalResponse> popularAnimal = animals.stream()
                .filter(animal -> animal.isPopularAnimal())
                .map(AnimalResponse::from)
                .toList();

        Map<AnimalType, List<AnimalResponse>> groupedAnimals = animals.stream()
                .map(AnimalResponse::from)
                .collect(Collectors.groupingBy(animalResponse ->
                        AnimalType.valueOf(animalResponse.animalType().getType())
                ));

        List<AnimalResponse> mammals = groupedAnimals.getOrDefault(AnimalType.MAMMALS, Collections.emptyList());

        List<AnimalResponse> reptiles = groupedAnimals.getOrDefault(AnimalType.REPTILES, Collections.emptyList());

        List<AnimalResponse> fish = groupedAnimals.getOrDefault(AnimalType.FISH, Collections.emptyList());

        List<AnimalResponse> birds = groupedAnimals.getOrDefault(AnimalType.BIRDS, Collections.emptyList());

        return AnimalListResponse.from(
                popularAnimal,
                mammals,
                reptiles,
                fish,
                birds
        );
    }
}
