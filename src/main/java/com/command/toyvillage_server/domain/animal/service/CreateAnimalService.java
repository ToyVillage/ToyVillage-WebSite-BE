package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAnimalService {
    private final AnimalRepository animalRepository;
}
