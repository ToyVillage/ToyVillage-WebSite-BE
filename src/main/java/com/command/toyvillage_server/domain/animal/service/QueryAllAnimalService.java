package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAllAnimalService {
    private final AnimalRepository animalRepository;

    @Transactional(readOnly = true)
    public List<AnimalResponse> execute() {
        List<Animal> animals = animalRepository.findAll();

        return animals
                .stream()
                .map(AnimalResponse::from)
                .collect(Collectors.toList());
    }
}
