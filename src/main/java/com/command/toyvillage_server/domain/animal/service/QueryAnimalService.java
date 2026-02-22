package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.exception.AnimalNotFoundException;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryAnimalService {
    private final AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public AnimalResponse execute(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> AnimalNotFoundException.EXCEPTION);

        return AnimalResponse.from(animal);
    }
}
