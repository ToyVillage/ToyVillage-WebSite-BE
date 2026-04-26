package com.command.toyvillage_server.domain.animal.service;

import com.command.toyvillage_server.domain.animal.domain.Animal;
import com.command.toyvillage_server.domain.animal.domain.enums.AnimalType;
import com.command.toyvillage_server.domain.animal.domain.repository.AnimalRepository;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryAllAnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    private QueryAllAnimalService queryAllAnimalService;

    @BeforeEach
    void setUp() {
        queryAllAnimalService = new QueryAllAnimalService(animalRepository);
    }

    @Test
    void 전체_동물_목록을_종류별로_분류하고_인기동물을_별도로_반환한다() {
        when(animalRepository.findAll()).thenReturn(List.of(
                createAnimal(1L, "사자", AnimalType.MAMMALS, true),
                createAnimal(2L, "도마뱀", AnimalType.REPTILES, false),
                createAnimal(3L, "금붕어", AnimalType.FISH, true),
                createAnimal(4L, "앵무새", AnimalType.BIRDS, false)
        ));

        AnimalListResponse response = queryAllAnimalService.execute();

        assertEquals(2, response.popularAnimal().size());
        assertEquals("사자", response.popularAnimal().get(0).animalKind());
        assertEquals("금붕어", response.popularAnimal().get(1).animalKind());

        assertEquals(List.of("사자"), response.mammals().stream().map(animal -> animal.animalKind()).toList());
        assertEquals(List.of("도마뱀"), response.reptiles().stream().map(animal -> animal.animalKind()).toList());
        assertEquals(List.of("금붕어"), response.fish().stream().map(animal -> animal.animalKind()).toList());
        assertEquals(List.of("앵무새"), response.birds().stream().map(animal -> animal.animalKind()).toList());
    }

    @Test
    void 동물이_없으면_모든_목록은_빈_리스트를_반환한다() {
        when(animalRepository.findAll()).thenReturn(List.of());

        AnimalListResponse response = queryAllAnimalService.execute();

        assertTrue(response.popularAnimal().isEmpty());
        assertTrue(response.mammals().isEmpty());
        assertTrue(response.reptiles().isEmpty());
        assertTrue(response.fish().isEmpty());
        assertTrue(response.birds().isEmpty());
    }

    private Animal createAnimal(Long id, String animalKind, AnimalType animalType, boolean popularAnimal) {
        return Animal.builder()
                .id(id)
                .animalKind(animalKind)
                .animalImage("https://example.com/" + id)
                .animalDescription(animalKind + " 소개")
                .animalType(animalType)
                .popularAnimal(popularAnimal)
                .build();
    }
}
