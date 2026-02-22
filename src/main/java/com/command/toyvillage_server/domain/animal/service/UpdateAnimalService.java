package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateAnimalService {
    private final AnimalRepository animalRepository;

    public AnimalResponse execute(Long animalId) {
        animalRepository.findById(animalId)
                .orElseThrow(() -> );
    }

}
