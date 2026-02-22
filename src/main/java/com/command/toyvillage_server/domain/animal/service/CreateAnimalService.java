package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.presentation.dto.request.AnimalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAnimalService {
    private final AnimalRepository animalRepository;

    @Transactional
    public void execute(AnimalRequest request) {
        Animal animal = Animal.builder()
                .animalKind(request.animalKind())
                .animalDescription(request.animalDescription())
                .build();

        log.info("동물 소개 생성 / Id : {}", animal.getAnimalI    ());

        animalRepository.save(animal);
    }
}
