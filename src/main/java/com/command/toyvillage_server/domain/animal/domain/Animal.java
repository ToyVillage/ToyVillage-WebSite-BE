package com.command.toyvillage_server.domain.animal.domain;

import com.command.toyvillage_server.domain.animal.presentation.dto.request.AnimalRequest;
import com.command.toyvillage_server.domain.animal.presentation.dto.response.AnimalResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_animal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(nullable = false, length = 50, name = "animal_kind")
    private String animalKind;

    @Column(nullable = false, name = "animal_description")
    private String animalDescription;

    public void update(String animalKind, String animalDescription) {
        this.animalKind = animalKind;
        this.animalDescription = animalDescription;
    }
}
