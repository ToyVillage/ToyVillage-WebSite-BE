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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "animal_id")
    private Long id;

    @Column(nullable = false, length = 50, name = "animal_kind")
    private String animalKind;

    @Column(nullable = false, name = "animal_discription")
    private String animalDescription;
}
