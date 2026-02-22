package com.command.toyvillage_server.domain.animal.presentation;

import com.command.toyvillage_server.domain.animal.presentation.dto.request.AnimalRequest;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import com.command.toyvillage_server.domain.animal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final CreateAnimalService createAnimalService;
    private final DeleteAnimalService deleteAnimalService;
    private final UpdateAnimalService updateAnimalService;
    private final QueryAnimalService queryAnimalService;
    private final QueryAllAnimalService queryAllAnimalService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AnimalRequest request) {
        createAnimalService.execute(request);

        return ResponseEntity
                .ok()
                .body("동물소개가 추가되었습니다.");
    }

    @PutMapping("/{animal_id}")
    public ResponseEntity<String> update(@RequestBody AnimalRequest request,  @PathVariable Long animalId) {
        updateAnimalService.execute(request, animalId);

        return ResponseEntity
                .ok()
                .body("동물소개가 수정되었습니다.");
    }

    @DeleteMapping("/{animal_id}")
    public ResponseEntity<String> delete(@PathVariable Long animalId) {
        deleteAnimalService.execute(animalId);

        return ResponseEntity
                .ok()
                .body("동물소개가 삭제되었습니다.");
    }

    @GetMapping("/{animal_id}")
    public AnimalResponse Query(@PathVariable Long animalId) {
        return queryAnimalService.execute(animalId);
    }

    @GetMapping
    public List<AnimalResponse> queryAll() {
        return queryAllAnimalService.execute();
    }
}
