package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.exception.AnimalNotFoundException;
import com.command.toyvillage_server.domain.animal.presentation.dto.request.AnimalRequest;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateAnimalService {
    private final AnimalRepository animalRepository;

    @Transactional
    public AnimalResponse execute(AnimalRequest request, Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> AnimalNotFoundException.EXCEPTION);

        animal.update(
                request.animalKind(),
                request.animalDescription()
        );

        animalRepository.save(animal);

        return new AnimalResponse(
                animal.getAnimalKind(),
                animal.getAnimalDescription()
        );
    }

}
