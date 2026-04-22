package com.command.toyvillage_server.domain.animal.domain;

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

    @Column(nullable = false, name = "animal_image")
    private String animalImage;

    @Column(nullable = false, name = "animal_description")
    private String animalDescription;

    private String animalType;

    public void update(String animalKind, String animalImage, String animalDescription) {
        this.animalKind = animalKind;
        this.animalImage = animalImage;
        this.animalDescription = animalDescription;
    }
}
