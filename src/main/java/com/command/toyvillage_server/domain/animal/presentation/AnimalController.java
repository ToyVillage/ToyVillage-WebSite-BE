package com.command.toyvillage_server.domain.animal.presentation;

import com.command.toyvillage_server.domain.animal.presentation.dto.request.AnimalRequest;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.MessageResponse;
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
    public ResponseEntity<MessageResponse> create(@RequestBody AnimalRequest request) {
        createAnimalService.execute(request);

        return ResponseEntity
                .ok(MessageResponse.of("동물소개가 생성되었습니다."));
    }

    @PutMapping("/{animal_id}")
    public ResponseEntity<MessageResponse> update(@RequestBody AnimalRequest request,  @PathVariable Long animal_id) {
        updateAnimalService.execute(request, animal_id);

        return ResponseEntity
                .ok(MessageResponse.of("동물소개가 생성되었습니다."));
    }

    @DeleteMapping("/{animal_id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long animal_id) {
        deleteAnimalService.execute(animal_id);

        return ResponseEntity
                .ok(MessageResponse.of("동물소개가 생성되었습니다."));
    }

    @GetMapping("/{animal_id}")
    public AnimalResponse Query(@PathVariable Long animal_id) {
        return queryAnimalService.execute(animal_id);
    }

    @GetMapping
    public List<AnimalResponse> queryAll() {
        return queryAllAnimalService.execute();
    }
}
